package demo.bookssample.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * {
"title": "Designing Across Senses",
"subtitle": "A Multimodal Approach to Product Design",
"isbn13": "9781491954249",
"price": "$27.59",
"image": "https://itbook.store/img/books/9781491954249.png",
"url": "https://itbook.store/books/9781491954249"
}
 */
@Entity(tableName = "books")
data class Book(@PrimaryKey(autoGenerate = true) val id: Int, val title: String, val subtitle: String, val isbn13: Long, val price: String, val image: String, val url: String) {

}