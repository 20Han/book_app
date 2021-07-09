package com.lee.book.ui.Detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lee.book.entitiy.DetailBook
import kotlinx.coroutines.launch

class DetailViewModel(
    private val detailRepository: IDetailRepository = DetailRepository()
) : ViewModel() {

    private val _detailBook = MutableLiveData<DetailBook>()
    val detailBook: LiveData<DetailBook> = _detailBook

    fun getDetailBook(isbn13 : String?){
        viewModelScope.launch {
            if(isbn13 != null)
                _detailBook.value = detailRepository.getDetailBook(isbn13)
        }
    }

}