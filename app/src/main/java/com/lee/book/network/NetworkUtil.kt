package com.lee.book.network

import com.lee.book.entitiy.DetailBook
import com.lee.book.entitiy.NewBooks
import com.lee.book.entitiy.SearchBooks
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

class NetworkUtil(){
    val apiBaseUrl = "https://api.itbook.store/"

    interface NewBookApi {
        @GET("1.0/new")
        fun getNewBookResult(): Call<NewBooks>?
    }

    interface SearchBookApi {
        @GET("1.0/search/{title}/{page}")
        fun getSearchBookResult(@Path("title") title : String, @Path("page") page : String): Call<SearchBooks>?
    }

    interface DetailBookApi {
        @GET("1.0/books/{isbn13}")
        fun getDetailBookResult(@Path("isbn13") isbn13 : String): Call<DetailBook>?
    }

    fun getNewBook() : NewBooks?{
        val retrofit = Retrofit.Builder()
            .baseUrl(apiBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val getNewBookApi = retrofit.create(NewBookApi::class.java)

        val newBookResponse = getNewBookApi.getNewBookResult()?.execute()

        return newBookResponse?.body()
    }

    fun searchBook(title : String, page : String) : SearchBooks?{
        val retrofit = Retrofit.Builder()
            .baseUrl(apiBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val searchBookApi = retrofit.create(SearchBookApi::class.java)

        val searchBookResponse = searchBookApi.getSearchBookResult(title, page)?.execute()

        return searchBookResponse?.body()
    }

    fun getDetailBook(isbn13: String) : DetailBook?{
        val retrofit = Retrofit.Builder()
            .baseUrl(apiBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val getDetailBookApi = retrofit.create(DetailBookApi::class.java)

        val detailBookResponse = getDetailBookApi.getDetailBookResult(isbn13)?.execute()

        return detailBookResponse?.body()
    }
}