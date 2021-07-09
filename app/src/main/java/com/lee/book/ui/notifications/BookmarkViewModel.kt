package com.lee.book.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lee.book.entitiy.Book

class BookmarkViewModel : ViewModel() {

    private val _bookMarkedBooks = MutableLiveData<List<Book>>()
    val bookMarkedBooks: LiveData<List<Book>> = _bookMarkedBooks

    fun saveBookMark(book : Book){
        _bookMarkedBooks.value = _bookMarkedBooks.value ?: ArrayList()

        _bookMarkedBooks.value?.plus(book)
    }

    fun deleteBookMark(book: Book){
        _bookMarkedBooks.value = _bookMarkedBooks.value ?: ArrayList()

        _bookMarkedBooks.value?.minus(book)
    }


}