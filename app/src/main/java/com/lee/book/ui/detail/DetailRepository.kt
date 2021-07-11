package com.lee.book.ui.detail

import android.content.Context
import androidx.room.Room
import com.lee.book.entitiy.DetailBook
import com.lee.book.network.NetworkUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface IDetailRepository{
    suspend fun getDetailBook(isbn13 : String) : DetailBook?
    suspend fun getDetailBookMemo(isbn13 : String) : Memo?
    suspend fun saveDetailBookMemo(memo : Memo?)
}

class DetailRepository() : IDetailRepository {
    private val networkUtil = NetworkUtil()

    override suspend fun getDetailBook(isbn13: String): DetailBook? {
        return withContext(Dispatchers.IO) {
            networkUtil.getDetailBook(isbn13)
        }
    }

    override suspend fun getDetailBookMemo(isbn13: String): Memo? {
        val memoString = MemoStorage.getInstance().getMemo(isbn13)
        return if(memoString != null)
            Memo(isbn13, memoString)
        else
            null
    }

    override suspend fun saveDetailBookMemo(memo: Memo?) {
        if(memo != null)
            MemoStorage.getInstance().saveMemo(memo)
    }
}
