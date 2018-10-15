package demo.bookssample.db

import androidx.test.runner.AndroidJUnit4
import demo.bookssample.LiveDataTestUtil.getValue
import demo.bookssample.entity.Book
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class BooksDaoTest : DBTest() {

    @Test
    fun insertAndRead() {
        val book = Book(id = 0, title = "Book1", subtitle = "Subtitle", isbn13 = 12345L, price = "$12.3", image = "", url = "")
        db.bookDao().insert(book)
        val loaded = getValue(db.bookDao().getBookByIsbn(12345L))
        assertThat(loaded, notNullValue())
        assertThat(loaded.title, CoreMatchers.`is`("Book1"))
        assertThat(loaded.subtitle, CoreMatchers.`is`("Subtitle"))
        assertThat(loaded.price, CoreMatchers.`is`("$12.3"))
        assertThat(loaded.image, CoreMatchers.`is`(equalTo("")))
        assertThat(loaded.url, CoreMatchers.`is`(equalTo("")))
    }

    @Test
    fun checkCount() {
        val book = Book(id = 0, title = "Book1", subtitle = "Subtitle", isbn13 = 12345L, price = "$12.3", image = "", url = "")
        db.bookDao().insert(book)
        val loaded = db.bookDao().getCount()
        assertThat(loaded, CoreMatchers.equalTo(1))
    }
}