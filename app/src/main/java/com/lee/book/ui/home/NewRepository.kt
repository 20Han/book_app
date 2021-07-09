package com.lee.book.ui.home

import com.lee.book.entitiy.NewBooks
import com.lee.book.network.NetworkUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface INewRepository{
    suspend fun getNewBooks() : NewBooks?
}

class NewRepository : INewRepository{
    private val networkUtil = NetworkUtil()

    override suspend fun getNewBooks() : NewBooks?{
        return withContext(Dispatchers.IO){
            networkUtil.getNewBook()
        }
    }
}