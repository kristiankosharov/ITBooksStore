package demo.bookssample.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "books")
data class Book(@PrimaryKey val id: Int, val title: String, val subtitle: String, val isbn13: Long, val price: Double, val image: String, val url: String) {

}