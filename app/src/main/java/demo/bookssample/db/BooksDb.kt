package demo.bookssample.db

import androidx.room.Database
import androidx.room.RoomDatabase
import demo.bookssample.entity.Book

@Database(entities = [Book::class], exportSchema = false, version = 1)
abstract class BooksDb : RoomDatabase() {
    abstract fun bookDao(): BooksDao
}