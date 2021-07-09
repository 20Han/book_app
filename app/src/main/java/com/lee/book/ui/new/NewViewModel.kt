package com.lee.book.ui.new

import androidx.lifecycle.*
import com.lee.book.entitiy.Book
import kotlinx.coroutines.launch

class NewViewModel(
    private val newRepository: INewRepository = NewRepository()
) : ViewModel() {

    private val _newBookList = MutableLiveData<List<Book>>()
    val newBookList: LiveData<List<Book>> = _newBookList

    fun getNewBooks(){
        viewModelScope.launch {
            _newBookList.value = newRepository.getNewBooks()
        }
    }
}