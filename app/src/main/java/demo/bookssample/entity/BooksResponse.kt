package demo.bookssample.entity


data class BooksResponse(val total: Int,val page: Int, val books: ArrayList<Book>) {}