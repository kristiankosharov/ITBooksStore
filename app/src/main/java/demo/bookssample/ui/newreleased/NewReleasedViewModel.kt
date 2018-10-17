package demo.bookssample.ui.newreleased

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import demo.bookssample.OpenForTesting
import demo.bookssample.entity.BooksResponse
import demo.bookssample.entity.Resource
import demo.bookssample.repository.BooksRepository
import java.util.logging.Logger
import javax.inject.Inject

@OpenForTesting
class NewReleasedViewModel @Inject constructor(private val repository: BooksRepository) : ViewModel() {
    private var _books: LiveData<Resource<BooksResponse>> = repository.loadNewReleasedBooks()

    val books: LiveData<Resource<BooksResponse>>
        get() {
            _books = repository.loadNewReleasedBooks()
            Logger.getLogger("TESt").info("IS IT NULL: ${_books == null} AND VALUE ${_books.value}")
            return _books
        }
}