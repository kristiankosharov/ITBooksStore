package demo.bookssample.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import demo.bookssample.OpenForTesting
import demo.bookssample.entity.Book

@Dao
@OpenForTesting
interface BooksDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(books: List<Book>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(books: Book)

    @Query("SELECT * FROM books")
    fun getBooks(): LiveData<List<Book>>

    @Query("SELECT * FROM books WHERE newStatus LIKE 1")
    fun getNewReleasedBooks(): LiveData<List<Book>>

    @Query("SELECT * FROM books WHERE isbn13 LIKE :isbn")
    fun getBookByIsbn(isbn: Long): LiveData<Book>

    @Query("UPDATE books SET newStatus = 0 WHERE newStatus LIKE 1")
    fun clearNewStatus()

    @Query("SELECT COUNT(*) FROM books")
    fun getCount(): Int
}