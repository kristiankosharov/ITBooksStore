package demo.bookssample.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import demo.bookssample.ApiUtil
import demo.bookssample.ApiUtil.successCall
import demo.bookssample.InstantAppExecutors
import demo.bookssample.api.BooksService
import demo.bookssample.db.BooksDao
import demo.bookssample.db.BooksDb
import demo.bookssample.entity.Book
import demo.bookssample.entity.BooksResponse
import demo.bookssample.entity.Resource
import demo.bookssample.mock
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BooksRepositoryTest {
    private lateinit var repository: BooksRepository
    private val dao = mock<BooksDao>()
    private val service = mock<BooksService>()
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        val db = mock<BooksDb>()
        `when`(db.bookDao()).thenReturn(dao)
        `when`(db.runInTransaction(ArgumentMatchers.any())).thenCallRealMethod()
        repository = BooksRepository(InstantAppExecutors(), dao, service)
    }

    @Test
    fun checkNotNull() {
        assertThat(repository.loadNewReleasedBooks(), notNullValue())
    }

    @Test
    fun loadBooks() {
        repository.loadNewReleasedBooks()
        verify(dao).getNewReleasedBooks()
    }

    // shouldFetch should return false
    @Test
    fun dontGoToNetwork() {
        val dbData = MutableLiveData<List<Book>>()
        val book = Book(0, "Book1", "Subtitle1", 123456L, "$12.34", "", 0, "")
        val listBook = arrayListOf(book)
        dbData.value = listBook
        `when`(dao.getNewReleasedBooks()).thenReturn(dbData)
        val observer = mock<Observer<Resource<BooksResponse>>>()
        repository.loadNewReleasedBooks().observeForever(observer)

        verify(service, never()).getNewReleasedBooks()
    }

    @Test
    fun goToNetwork() {
        val dbData = MutableLiveData<List<Book>>()
        `when`(dao.getNewReleasedBooks()).thenReturn(dbData)
        val book = Book(0, "Book1", "Subtitle1", 123456L, "$12.34", "", 0, "")
        val listBook = arrayListOf(book)
        val bookResponse = BooksResponse(0, 1, 1, listBook)
        val call = ApiUtil.successCall(bookResponse)
        `when`(service.getNewReleasedBooks()).thenReturn(call)
        val observer = mock<Observer<Resource<BooksResponse>>>()

        repository.loadNewReleasedBooks().observeForever(observer)
        verify(service, never()).getNewReleasedBooks()
        val updatedDbData = MutableLiveData<List<Book>>()
        `when`(dao.getNewReleasedBooks()).thenReturn(updatedDbData)
        dbData.value = null
        verify(service).getNewReleasedBooks()
    }

    @Test
    fun loadBooksFromNetwork() {
        val dbData = MutableLiveData<List<Book>>()
        `when`(dao.getNewReleasedBooks()).thenReturn(dbData)

        val book = Book(0, "Book1", "Subtitle1", 123456L, "$12.34", "", 0, "")
        val booksResponse = BooksResponse(0, 1, 1, arrayListOf(book))
        val call = successCall(booksResponse)
        `when`(service.getNewReleasedBooks()).thenReturn(call)

        val data = repository.loadNewReleasedBooks()
        verify(dao).getNewReleasedBooks()
        verifyNoMoreInteractions(service)

        val observer = mock<Observer<Resource<BooksResponse>>>()
        data.observeForever(observer)
        verifyNoMoreInteractions(service)
        verify(observer).onChanged(Resource.loading(null))
        val updatedDbData = MutableLiveData<List<Book>>()
        `when`(dao.getNewReleasedBooks()).thenReturn(updatedDbData)

        updatedDbData.postValue(null)
        verify(service).getNewReleasedBooks()
        verify(dao).insert(book)

        updatedDbData.postValue(arrayListOf(book))
        verify(observer).onChanged(Resource.success(booksResponse))
    }


}