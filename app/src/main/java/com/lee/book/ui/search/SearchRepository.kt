package com.lee.book.ui.search

import com.lee.book.entitiy.Book
import com.lee.book.network.NetworkUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface ISearchRepository{
    suspend fun searchBooks(title : String, page : String) : List<Book>
}

class SearchRepository : ISearchRepository{
    private val networkUtil = NetworkUtil()

    override suspend fun searchBooks(title : String, page : String) : List<Book> {
        return withContext(Dispatchers.IO){
            networkUtil.searchBook(title, page)?.books ?: ArrayList()
        }
    }
}