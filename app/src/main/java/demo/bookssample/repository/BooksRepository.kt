package demo.bookssample.repository

import androidx.lifecycle.LiveData
import demo.bookssample.AppExecutors
import demo.bookssample.api.BooksService
import demo.bookssample.db.BooksDao
import demo.bookssample.entity.ApiResponse
import demo.bookssample.entity.BooksResponse
import demo.bookssample.entity.Resource
import javax.inject.Inject


class BooksRepository @Inject constructor(
        private val appExecutors: AppExecutors,
        private val booksDao: BooksDao,
        private val booksService: BooksService
) {

    fun loadNewReleasedBooks(): LiveData<Resource<BooksResponse>> {
        return object : NetworkBoundResource<BooksResponse, BooksResponse>(appExecutors) {
            override fun saveCallResult(item: BooksResponse) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun shouldFetch(data: BooksResponse?): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun loadFromDb(): LiveData<BooksResponse> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun createCall(): LiveData<ApiResponse<BooksResponse>> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        }.asLiveData()
    }
}