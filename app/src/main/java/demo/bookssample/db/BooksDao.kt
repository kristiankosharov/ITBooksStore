package demo.bookssample.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import demo.bookssample.entity.Book

@Dao
interface BooksDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(book: Book)
}