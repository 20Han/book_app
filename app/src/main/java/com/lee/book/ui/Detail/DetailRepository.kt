package com.lee.book.ui.detail

import com.lee.book.entitiy.DetailBook
import com.lee.book.network.NetworkUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface IDetailRepository{
    suspend fun getDetailBook(isbn13 : String) : DetailBook?
}

class DetailRepository : IDetailRepository {
    private val networkUtil = NetworkUtil()
    override suspend fun getDetailBook(isbn13: String): DetailBook? {
        return withContext(Dispatchers.IO) {
            networkUtil.getDetailBook(isbn13)
        }
    }
}
