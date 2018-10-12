package demo.bookssample.db

import android.os.Environment
import androidx.room.Database
import androidx.room.RoomDatabase
import demo.bookssample.entity.Book
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.logging.Logger

@Database(entities = [Book::class], exportSchema = false, version = 1)
abstract class BooksDb : RoomDatabase() {
    abstract fun bookDao(): BooksDao

    companion object {
        fun exportDB(packageName: String) {
            try {
                val sd = Environment.getExternalStorageDirectory()
                Logger.getLogger(BooksDb::class.java.name).info("Start exporting")

                if (sd.canWrite()) {
                    Logger.getLogger(BooksDb::class.java.name).info("SD card can write")
                    val currentDBPath = "/data/data/$packageName/databases/Books.db"
                    val backupDBPath = "Books.db"
                    val currentDB = File(currentDBPath)
                    val backupDB = File(sd, backupDBPath)
                    if (!backupDB.exists()) backupDB.createNewFile()
                    if (currentDB.exists()) {
                        val src = FileInputStream(currentDB).channel
                        val dst = FileOutputStream(backupDB).channel
                        dst.transferFrom(src, 0, src.size())
                        src.close()
                        dst.close()
                        Logger.getLogger(BooksDb::class.java.name).info("Exported DB")
                    }
                } else {
                    Logger.getLogger(BooksDb::class.java.name).info("SD card NOT can write")
                }
            } catch (e: Exception) {
                Logger.getLogger(BooksDb::class.java.name).info(e.message)
                e.printStackTrace()
            }

        }
    }
}