package com.lee.book.ui.detail

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

    private val _detailBookMemo = MutableLiveData<Memo>()
    val detailBookMemo: LiveData<Memo> = _detailBookMemo

    fun getDetailBook(isbn13 : String?){
        viewModelScope.launch {
            if(isbn13 != null)
                _detailBook.value = detailRepository.getDetailBook(isbn13)
        }
    }

    fun getDetailBookMemo(isbn13 : String?){
        viewModelScope.launch {
            if(isbn13 != null)
                _detailBookMemo.value = detailRepository.getDetailBookMemo(isbn13) ?: Memo(isbn13, "")
        }
    }

    fun saveDetailBookMemo(memoString : String){
        viewModelScope.launch {
            _detailBookMemo.value?.memoString = memoString
            detailRepository.saveDetailBookMemo(_detailBookMemo.value)
        }
    }
}