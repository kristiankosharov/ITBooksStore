package demo.bookssample.ui


import androidx.databinding.DataBindingComponent
import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import demo.bookssample.MainActivity
import demo.bookssample.R
import demo.bookssample.binding.FragmentBindingAdapters
import demo.bookssample.entity.Book
import demo.bookssample.entity.BooksResponse
import demo.bookssample.entity.Resource
import demo.bookssample.ui.newreleased.NewReleasedFragment
import demo.bookssample.ui.newreleased.NewReleasedViewModel
import demo.bookssample.util.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
class NewReleasedFragmentTest {
    @Rule
    @JvmField
    val activityRule = ActivityTestRule(MainActivity::class.java, true, true)
    @Rule
    @JvmField
    val executorRule = TaskExecutorWithIdlingResourceRule()
    @Rule
    @JvmField
    val countingAppExecutors = CountingAppExecutorsRule()
    @Rule
    @JvmField
    val dataBindingIdlingResourceRule = DataBindingIdlingResourceRule(activityRule)

    private val booksLiveData = MutableLiveData<Resource<BooksResponse>>()
    private lateinit var viewModel: NewReleasedViewModel
    private lateinit var mockBindingAdapter: FragmentBindingAdapters
    private val newReleasedFragment = NewReleasedFragment()
    // You can pass argument:
//            .apply {
//                arguments = {Bundle with arguments}
//            }

    @Before
    fun init() {
        newReleasedFragment.viewModelFactory = ViewModelUtil.createFor(viewModel)
        newReleasedFragment.appExecutors = countingAppExecutors.appExecutors
        viewModel = Mockito.mock(NewReleasedViewModel::class.java)
        mockBindingAdapter = Mockito.mock(FragmentBindingAdapters::class.java)
        Mockito.`when`(viewModel.books).thenReturn(booksLiveData)
        newReleasedFragment.dataBindingComponent = object : DataBindingComponent {
            override fun getFragmentBindingAdapters(): FragmentBindingAdapters {
                return mockBindingAdapter
            }
        }
    }

    @Test
    fun testBooks() {
        setBooks()
        onView(listMatcher().atPosition(0)).check(matches(ViewMatchers.hasDescendant(withText("Book1"))))
    }

    private fun listMatcher(): RecyclerViewMatcher {
        return RecyclerViewMatcher(R.id.new_released_books)
    }

    private fun setBooks() {
        val book = Book(id = 0, title = "Book1", subtitle = "Subtitle", isbn13 = 12345L, price = "$12.3", image = "", url = "")
        val bookResponse = BooksResponse(0, 1, 1, listOf(book) as ArrayList<Book>)
        booksLiveData.postValue(Resource.success(bookResponse))
    }
}