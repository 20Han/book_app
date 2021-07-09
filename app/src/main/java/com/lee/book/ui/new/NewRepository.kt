package com.lee.book.ui.new

import com.lee.book.entitiy.Book
import com.lee.book.network.NetworkUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface INewRepository{
    suspend fun getNewBooks() : List<Book>
}

class NewRepository : INewRepository{
    private val networkUtil = NetworkUtil()

    override suspend fun getNewBooks() : List<Book> {
        return withContext(Dispatchers.IO){
            networkUtil.getNewBook()?.books ?: ArrayList()
        }
    }
}