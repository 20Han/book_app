package com.lee.book

import android.util.Log
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class NetworkTest(){
    data class Book(
            var title: String,
            var subTitle: String,
            var isbn13: String,
            var price: String,
            var image: String,
            var url: String
    )

    data class BooksArray(
            var error : String,
            var total : String,
            val books : List<Book>
    )

    interface GetNewBookApi {
        @GET("1.0/new")
        fun getNewBookResult(): Call<BooksArray>?
    }

    @Test
    fun retrofitTest() {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.itbook.store/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val getNewBookApi = retrofit.create(GetNewBookApi::class.java)

        getNewBookApi.getNewBookResult()?.enqueue(object : Callback<BooksArray> {
            override fun onFailure(call: Call<BooksArray>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<BooksArray>, response: Response<BooksArray>) {
                var error = response.body()?.error
                var total = response.body()?.total
                var books = response.body()?.books

                val i = 1
                books?.forEach {
                    var title: String = it.title
                    var subTitle: String = it.subTitle
                    var isbn13: String = it.isbn13
                    var price: String = it.price
                    var image: String = it.image
                    var url: String = it.url
                    Log.d("test", "book${i}:\n\t" +
                            "title: $title\n\t" +
                            "subTitle: $subTitle\n\t" +
                            "isbn13: $isbn13\n\t" +
                            "price: $price\n\t" +
                            "image: $image\n\t" +
                            "url: $url\n\t"
                    )
                }
            }

        })

        Thread.sleep(5_000)
    }
}