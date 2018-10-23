package demo.bookssample.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.GsonBuilder
import demo.bookssample.LiveDataTestUtil.getValue
import demo.bookssample.entity.ApiSuccessResponse
import demo.bookssample.entity.Book
import demo.bookssample.entity.BooksResponse
import demo.bookssample.util.LiveDataCallAdapterFactory
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.*
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@RunWith(JUnit4::class)
class BookServiceTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: BooksService
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        val gson = GsonBuilder()
                .setLenient()
                .create()
        service = Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .build()
                .create(BooksService::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun getBooks() {
        enqueueResponse()
        val response = getValue(service.getNewReleasedBooks())
        assertThat(response, instanceOf(ApiSuccessResponse::class.java))

        val repos = (response as ApiSuccessResponse).body
        val request = mockWebServer.takeRequest()
        assertThat(request.path, CoreMatchers.`is`("/new"))
        assertThat(repos, notNullValue())
        assertThat(repos.books, `is`(emptyList<Book>()))
    }

    private fun enqueueResponse(headers: Map<String, String> = emptyMap()) {
        val response = BooksResponse(0, 1, 1, ArrayList())
        val jsonResponse = GsonBuilder().create().toJson(response)
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(
                mockResponse
                        .setBody(jsonResponse.toString())
        )
    }
}