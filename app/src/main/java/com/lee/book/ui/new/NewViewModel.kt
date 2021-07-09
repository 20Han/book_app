package com.lee.book.ui.new

import androidx.lifecycle.*
import com.lee.book.entitiy.Book
import kotlinx.coroutines.launch

class NewViewModel(
    private val newRepository: INewRepository = NewRepository()
) : ViewModel() {

    private val _bookList = MutableLiveData<List<Book>>()
    val bookList: LiveData<List<Book>> = _bookList

    fun getNewBooks(){
        viewModelScope.launch {
            _bookList.value = newRepository.getNewBooks()
        }
    }
}