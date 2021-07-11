package com.lee.book.viewmodel

import androidx.test.InstrumentationRegistry
import com.lee.book.entitiy.Book
import com.lee.book.ui.search.SearchRepository
import com.lee.book.ui.search.SearchViewModel
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Assert
import org.junit.Assert.assertTrue
import org.junit.Test

class SearchViewModelTest {
    @Test
    fun testSearchBooks_isSearchingFalse_nonEmptyBookList() {
        val listBooks = ArrayList<Book>()
        val book = Book("title", "subTitle", "isbn13", "price", "image", "url")
        listBooks.add(book)

        val searchRepository = mockk<SearchRepository>()
        coEvery { searchRepository.searchBooks(any(), any()) } returns listBooks

        val searchViewModel = SearchViewModel(searchRepository)
        searchViewModel.searchBooks("", "1", InstrumentationRegistry.getContext())
        Thread.sleep(500)
        val returnListBooks = searchViewModel.searchBookList
        val returnBook = returnListBooks.value?.get(0)

        Assert.assertEquals(book.title, returnBook?.title)
        Assert.assertEquals(book.subtitle, returnBook?.subtitle)
        Assert.assertEquals(book.isbn13, returnBook?.isbn13)
        Assert.assertEquals(book.price, returnBook?.price)
        Assert.assertEquals(book.image, returnBook?.image)
        Assert.assertEquals(book.url, returnBook?.url)
    }

    @Test
    fun testSearchBooks_isSearchingFalse_emptyBookList() {
        val listBooks = ArrayList<Book>()

        val searchRepository = mockk<SearchRepository>()
        coEvery { searchRepository.searchBooks(any(), any()) } returns listBooks

        val searchViewModel = SearchViewModel(searchRepository)
        searchViewModel.searchBooks("", "1", InstrumentationRegistry.getContext())
        Thread.sleep(500)
        val returnListBooks = searchViewModel.searchBookList
        assertTrue(returnListBooks.value.isNullOrEmpty())
    }

    @Test
    fun testSearchBooks_isSearchingTrue_nonEmptyBookList() {
        val listBooks = ArrayList<Book>()
        val book = Book("title", "subTitle", "isbn13", "price", "image", "url")
        listBooks.add(book)

        val searchRepository = mockk<SearchRepository>()
        coEvery { searchRepository.searchBooks(any(), any()) } returns listBooks

        val searchViewModel = SearchViewModel(searchRepository)
        searchViewModel.isSearching.set(true)
        searchViewModel.searchBooks("", "1", InstrumentationRegistry.getContext())
        Thread.sleep(500)
        assertTrue(searchViewModel.searchBookList.value.isNullOrEmpty())
    }

}