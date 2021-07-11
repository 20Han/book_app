package com.lee.book.ui.detail

import android.util.ArrayMap

class MemoStorage private constructor(){
    var storage = ArrayMap<String, String>()

    fun saveMemo(memo : Memo){
        storage[memo.isbn13] = memo.memoString
    }

    fun getMemo(isbn13 : String) : String?{
        return storage[isbn13]
    }

    companion object {
        @Volatile private var instance: MemoStorage? = null

        @JvmStatic fun getInstance(): MemoStorage =
            instance ?: synchronized(this) {
                instance ?: MemoStorage().also {
                    instance = it
                }
            }
    }
}


data class Memo(
    var isbn13 : String,
    var memoString : String
)