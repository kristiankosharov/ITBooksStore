package demo.bookssample.entity

/**
 * {
"total": "0",
"total": "10",
"books": [
{
"title": "Designing Across Senses",
"subtitle": "A Multimodal Approach to Product Design",
"isbn13": "9781491954249",
"price": "$27.59",
"image": "https://itbook.store/img/books/9781491954249.png",
"url": "https://itbook.store/books/9781491954249"
},
...
]
}

 */
data class BooksResponse(val error: Int, val total: Int,val page: Int, val books: ArrayList<Book>) {}