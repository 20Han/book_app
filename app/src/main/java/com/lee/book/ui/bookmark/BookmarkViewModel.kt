package com.lee.book.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lee.book.entitiy.Book

class BookmarkViewModel : ViewModel() {

    private val _bookMarkedBooks = MutableLiveData<ArrayList<Book>>()
    val bookMarkedBooks: LiveData<ArrayList<Book>> = _bookMarkedBooks

    fun saveBookMark(book : Book){
        _bookMarkedBooks.value = _bookMarkedBooks.value ?: ArrayList()

        if(bookMarkedBooks.value?.contains(book)== null || !bookMarkedBooks.value?.contains(book)!!)
            bookMarkedBooks.value?.add(book)
    }

    fun deleteBookMark(book: Book){
        _bookMarkedBooks.value = _bookMarkedBooks.value ?: ArrayList()

        _bookMarkedBooks.value?.remove(book)
    }

    fun isRegisteredBookmark(book : Book) : Boolean{
        return bookMarkedBooks.value?.contains(book)!= null && bookMarkedBooks.value?.contains(book)!!
    }


}