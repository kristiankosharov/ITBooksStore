package demo.bookssample.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import demo.bookssample.AppExecutors
import demo.bookssample.api.BooksService
import demo.bookssample.db.BooksDao
import demo.bookssample.entity.ApiResponse
import demo.bookssample.entity.Book
import demo.bookssample.entity.BooksResponse
import demo.bookssample.entity.Resource
import java.util.logging.Logger
import javax.inject.Inject


class BooksRepository @Inject constructor(
        private val appExecutors: AppExecutors,
        private val booksDao: BooksDao,
        private val booksService: BooksService
) {
    private val logger = Logger.getLogger(BooksRepository::class.java.name)

    fun loadNewReleasedBooks(): LiveData<Resource<BooksResponse>> {
        return object : NetworkBoundResource<BooksResponse, BooksResponse>(appExecutors) {

            override fun onFetchFailed() {
                super.onFetchFailed()

                logger.info("Fetch fail")
            }

            override fun saveCallResult(item: BooksResponse) {
                logger.info("SAVE FROM API ${item.books.size}")
                booksDao.insert(item.books)
            }

            override fun shouldFetch(data: BooksResponse?): Boolean {
                val result = data == null || data.books.isEmpty()
                logger.info("SHOULD FETCH $result ")
                return result
            }

            override fun loadFromDb(): LiveData<BooksResponse> {

                logger.info("LOAD FROM DB")
                return Transformations
                        .map(booksDao.getBooks()) {
                            logger.info("Books from DB ${it.size}")
                            return@map BooksResponse(0, it.size, 1, it as ArrayList<Book>)
                        }
            }

            override fun createCall(): LiveData<ApiResponse<BooksResponse>> {
                logger.info("CALL NEW RELEASED BOOKS")
                return booksService.getNewReleasedBooks()
            }

        }.asLiveData()
    }
}