package demo.bookssample.api

import demo.bookssample.entity.ApiErrorResponse
import demo.bookssample.entity.ApiResponse
import demo.bookssample.entity.ApiSuccessResponse
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import retrofit2.Response


class ApiResponseTest {
    @Test
    fun exception() {
        val exception = Exception("ex")
        val (errorMessage) = ApiResponse.create<String>(exception)
        assertThat(errorMessage, CoreMatchers.`is`("ex"))
    }

    @Test
    fun success() {
        val apiResponse: ApiSuccessResponse<String> = ApiResponse.create(Response.success("body")) as ApiSuccessResponse<String>
        assertThat(apiResponse.body, CoreMatchers.`is`("body"))
        assertThat<Int>(apiResponse.nextPage, CoreMatchers.`is`(CoreMatchers.nullValue()))
    }

    @Test
    fun error() {
        val errorResponse = Response.error<String>(
                400,
                ResponseBody.create(MediaType.parse("application/txt"), "Error")
        )
        val (errorMessage) = ApiResponse.create<String>(errorResponse) as ApiErrorResponse
        assertThat(errorMessage, CoreMatchers.`is`("Error"))
    }
}