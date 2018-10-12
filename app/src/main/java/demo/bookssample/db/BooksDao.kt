package demo.bookssample.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import demo.bookssample.entity.Book

@Dao
interface BooksDao {
    @Insert()
    fun insert(books: List<Book>)

    @Query("SELECT * FROM books")
    fun getBooks(): LiveData<List<Book>>

    @Query("SELECT COUNT(*) FROM books")
    fun getCount(): Int
}