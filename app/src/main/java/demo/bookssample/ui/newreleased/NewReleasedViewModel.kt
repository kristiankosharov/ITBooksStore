package demo.bookssample.ui.newreleased

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import demo.bookssample.entity.BooksResponse
import demo.bookssample.entity.Resource
import demo.bookssample.repository.BooksRepository
import javax.inject.Inject


class NewReleasedViewModel @Inject constructor(repository: BooksRepository) : ViewModel() {
    private val books : LiveData<Resource<BooksResponse>> = repository.loadNewReleasedBooks()

    fun getNewBooks() = books
}