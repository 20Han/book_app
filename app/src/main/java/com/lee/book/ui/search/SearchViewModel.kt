package com.lee.book.ui.search

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lee.book.entitiy.Book
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean

class SearchViewModel(
    private val searchRepository : ISearchRepository = SearchRepository()
) : ViewModel() {

    private val _searchBookList = MutableLiveData<List<Book>>()
    val searchBookList: LiveData<List<Book>> = _searchBookList

    private var isSearching = AtomicBoolean(false)


    fun searchBooks(title: String, page: String, context: Context?) {
        viewModelScope.launch {
            if(isSearching.get()) {
                Toast.makeText(context, "previous search task is not finished yet", Toast.LENGTH_LONG).show()
                return@launch
            }

            isSearching.set(true)

            val searchResult = searchRepository.searchBooks(title, page)
            if(searchResult.isEmpty())
                Toast.makeText(context, "No Result for $title", Toast.LENGTH_LONG).show()
            else
                _searchBookList.value = searchRepository.searchBooks(title, page)

            isSearching.set(false)
        }
    }
}