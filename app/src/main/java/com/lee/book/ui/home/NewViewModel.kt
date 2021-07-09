package com.lee.book.ui.home

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class NewViewModel(
    private val newRepository: INewRepository = NewRepository()
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is New Fragment"
    }
    val text: LiveData<String> = _text

    fun getNewBooks(){
        viewModelScope.launch {
            val newBooks = newRepository.getNewBooks()
            if(newBooks == null)
                _text.value =  "Error in Network"
            else
                _text.value =  newBooks.toString()
        }
    }
}