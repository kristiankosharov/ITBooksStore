package demo.bookssample.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import demo.bookssample.mock
import demo.bookssample.repository.BooksRepository
import demo.bookssample.ui.newreleased.NewReleasedViewModel
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class NewReleasedViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private var repository: BooksRepository = mock()
    private var viewModel: NewReleasedViewModel = mock()

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
//        repository = BooksRepository(InstantAppExecutors(), dao, service)
//        viewModel = NewReleasedViewModel(repository = repository)
    }

    @Test
    fun testNull() {
        assertThat(viewModel, notNullValue())
        assertThat(repository, notNullValue())
        assertThat(viewModel.books, notNullValue())
//        val observer = mock<Observer<Resource<BooksResponse>>>()
//        viewModel.books.observeForever(observer)
        Mockito.verify(repository, Mockito.times(1)).loadNewReleasedBooks()
    }

    @Test
    fun timesInvokedRepo() {
        assertThat(viewModel.books, notNullValue())
        Mockito.verify(repository, Mockito.times(1)).loadNewReleasedBooks()
    }

    @Test
    fun newReleasedBooks() {
//        val observer = Mockito.mock(Observer<Resource<BooksResponse>>)
//        viewModel.books.observeForever(observer)
//        Mockito.verifyNoMoreInteractions(observer)
//        Mockito.verifyNoMoreInteractions(repository)
//
//        Mockito.verify(repository).loadNewReleasedBooks()
    }

}