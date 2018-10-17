package demo.bookssample.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import demo.bookssample.AppExecutors
import demo.bookssample.OpenForTesting
import demo.bookssample.api.BooksService
import demo.bookssample.db.BooksDao
import demo.bookssample.entity.ApiResponse
import demo.bookssample.entity.Book
import demo.bookssample.entity.BooksResponse
import demo.bookssample.entity.Resource
import java.util.logging.Logger
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@OpenForTesting
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

            // Clear all records with empty status to remove out-of-date records
            override fun saveCallResult(item: BooksResponse) {
                logger.info("SAVE FROM API ${item.books.size}")
                booksDao.clearNewStatus()
                for (book in item.books) {
                    book.newStatus = 1
                }
                booksDao.insert(item.books)
            }

            override fun shouldFetch(data: BooksResponse?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<BooksResponse> {
                logger.info("LOAD FROM DB")
                return Transformations
                        .map(booksDao.getNewReleasedBooks()) {
                            val total: Int = it?.size ?: 1
                            val books: List<Book> = it ?: ArrayList()
                            return@map BooksResponse(0, total, 1, books as ArrayList<Book>)
                        }
            }

            override fun createCall(): LiveData<ApiResponse<BooksResponse>> {
                logger.info("CALL NEW RELEASED BOOKS")
                return booksService.getNewReleasedBooks()
            }

        }.asLiveData()
    }
}