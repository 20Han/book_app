package com.lee.book.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lee.book.entitiy.Book
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchRepository : ISearchRepository = SearchRepository()
) : ViewModel() {

    private val _searchBookList = MutableLiveData<List<Book>>()
    val searchBookList: LiveData<List<Book>> = _searchBookList

    fun searchBooks(title : String, page : String){
        viewModelScope.launch {
            _searchBookList.value = searchRepository.searchBooks(title, page)
        }
    }
}