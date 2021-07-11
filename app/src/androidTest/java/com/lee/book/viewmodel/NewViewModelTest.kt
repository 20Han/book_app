package com.lee.book.viewmodel

import com.lee.book.entitiy.Book
import com.lee.book.ui.new.NewRepository
import com.lee.book.ui.new.NewViewModel
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class NewViewModelTest {
    @Test
    fun testGetNewBooks_nonEmptyBookList() {
        val listBooks = ArrayList<Book>()
        val book = Book("title", "subTitle", "isbn13", "price", "image", "url")
        listBooks.add(book)

        val newRepository = mockk<NewRepository>()
        coEvery { newRepository.getNewBooks() } returns listBooks

        val newViewModel = NewViewModel(newRepository)
        newViewModel.getNewBooks()
        Thread.sleep(500)
        val returnListBooks = newViewModel.newBookList
        val returnBook = returnListBooks.value?.get(0)

        assertEquals(book.title, returnBook?.title)
        assertEquals(book.subtitle, returnBook?.subtitle)
        assertEquals(book.isbn13, returnBook?.isbn13)
        assertEquals(book.price, returnBook?.price)
        assertEquals(book.image, returnBook?.image)
        assertEquals(book.url, returnBook?.url)
    }

    @Test
    fun testGetNewBooks_emptyBookList() {
        val newRepository = mockk<NewRepository>()
        coEvery { newRepository.getNewBooks() } returns ArrayList()

        val newViewModel = NewViewModel(newRepository)
        newViewModel.getNewBooks()
        Thread.sleep(500)
        val returnListBooks = newViewModel.newBookList
        assertTrue(returnListBooks.value.isNullOrEmpty())
    }
}