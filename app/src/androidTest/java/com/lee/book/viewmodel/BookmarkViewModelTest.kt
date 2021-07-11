package com.lee.book.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lee.book.entitiy.Book
import com.lee.book.ui.bookmark.BookmarkViewModel
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test


class BookmarkViewModelTest {
    @get:Rule
    var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun saveBookMark() {
        val book = Book("title", "subTitle", "isbn13", "price", "image", "url")
        val bookmarkViewModel = BookmarkViewModel()

        bookmarkViewModel.saveBookMark(book)

        assertEquals(book.title, bookmarkViewModel.bookMarkedBooks.value?.get(0)?.title)
        assertEquals(book.subtitle, bookmarkViewModel.bookMarkedBooks.value?.get(0)?.subtitle)
        assertEquals(book.isbn13, bookmarkViewModel.bookMarkedBooks.value?.get(0)?.isbn13)
        assertEquals(book.price, bookmarkViewModel.bookMarkedBooks.value?.get(0)?.price)
        assertEquals(book.image, bookmarkViewModel.bookMarkedBooks.value?.get(0)?.image)
        assertEquals(book.url, bookmarkViewModel.bookMarkedBooks.value?.get(0)?.url)
    }

    @Test
    fun deleteBookMark() {
        val book = Book("title", "subTitle", "isbn13", "price", "image", "url")
        val bookmarkViewModel = BookmarkViewModel()

        bookmarkViewModel.saveBookMark(book)

        assertEquals(book.title, bookmarkViewModel.bookMarkedBooks.value?.get(0)?.title)
        assertEquals(book.subtitle, bookmarkViewModel.bookMarkedBooks.value?.get(0)?.subtitle)
        assertEquals(book.isbn13, bookmarkViewModel.bookMarkedBooks.value?.get(0)?.isbn13)
        assertEquals(book.price, bookmarkViewModel.bookMarkedBooks.value?.get(0)?.price)
        assertEquals(book.image, bookmarkViewModel.bookMarkedBooks.value?.get(0)?.image)
        assertEquals(book.url, bookmarkViewModel.bookMarkedBooks.value?.get(0)?.url)

        bookmarkViewModel.deleteBookMark(book)
        assertTrue(bookmarkViewModel.bookMarkedBooks.value!!.isEmpty())
    }
}