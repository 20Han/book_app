package com.lee.book.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lee.book.entitiy.Book

class BookmarkViewModel : ViewModel() {

    private val _bookMarkedBooks = MutableLiveData<ArrayList<Book>>()
    val bookMarkedBooks: LiveData<ArrayList<Book>> = _bookMarkedBooks

    init {
        _bookMarkedBooks.value = ArrayList()
    }

    fun saveBookMark(book : Book){
        if(bookMarkedBooks.value?.contains(book)== null || !bookMarkedBooks.value?.contains(book)!!)
            bookMarkedBooks.value?.add(book)
    }

    fun deleteBookMark(book: Book){
        _bookMarkedBooks.value?.remove(book)
    }

    fun isRegisteredBookmark(book : Book) : Boolean{
        return bookMarkedBooks.value?.contains(book)!= null && bookMarkedBooks.value?.contains(book)!!
    }


}