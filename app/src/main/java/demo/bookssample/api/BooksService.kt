package demo.bookssample.api

import androidx.lifecycle.LiveData
import demo.bookssample.entity.ApiResponse
import demo.bookssample.entity.BooksResponse
import retrofit2.http.GET


interface BooksService {

    @GET("new")
    fun getNewReleasedBooks() : LiveData<ApiResponse<BooksResponse>>
}