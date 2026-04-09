package edu.unicauca.aplimovil.ufriendly.network

import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://openlibrary.org/"
private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
interface OpenLibraryApi {
    // Search books by subject/query
    @GET("search.json")
    suspend fun searchBooks(
        @Query("q") query: String,
        @Query("limit") limit: Int = 10,
        @Query("fields") fields: String = "title,author_name,cover_i,first_publish_year,key"
    ): BookResponse
}
object LibraryApi{
    val retrofitService : OpenLibraryApi by lazy{
        retrofit.create(OpenLibraryApi::class.java)
    }
}