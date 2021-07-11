package com.lee.book.viewmodel

import com.lee.book.entitiy.DetailBook
import com.lee.book.entitiy.Pdf
import com.lee.book.ui.detail.DetailRepository
import com.lee.book.ui.detail.DetailViewModel
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.spyk
import org.junit.Assert.*
import org.junit.Test

class DetailViewModelTest {
    @Test
    fun testGetDetailBook_notNullDetailBook() {
        val detailBook = DetailBook("title", "subTitle", "isbn13", "price", "image", "url",
            "isbn10", "isbn13", "1", "year", "rating", "desc", "price", "image", "url", Pdf("freeEBook"))

        val detailRepository = mockk<DetailRepository>()
        coEvery { detailRepository.getDetailBook(any()) } returns detailBook

        val detailViewModel = DetailViewModel(detailRepository)
        detailViewModel.getDetailBook("isbn13")
        Thread.sleep(500)
        val returnBook = detailViewModel.detailBook.value

        assertEquals(detailBook.title, returnBook?.title)
        assertEquals(detailBook.subtitle, returnBook?.subtitle)
        assertEquals(detailBook.isbn13, returnBook?.isbn13)
        assertEquals(detailBook.price, returnBook?.price)
        assertEquals(detailBook.image, returnBook?.image)
        assertEquals(detailBook.isbn10, returnBook?.isbn10)
        assertEquals(detailBook.authors, returnBook?.authors)
        assertEquals(detailBook.desc, returnBook?.desc)
        assertEquals(detailBook.language, returnBook?.language)
        assertEquals(detailBook.pages, returnBook?.pages)
        assertEquals(detailBook.publisher, returnBook?.publisher)
        assertEquals(detailBook.rating, returnBook?.rating)
        assertEquals(detailBook.year, returnBook?.year)
    }

    @Test
    fun testGetDetailBook_nullDetailBook() {
        val detailRepository = mockk<DetailRepository>()
        coEvery { detailRepository.getDetailBook(any()) } returns null

        val detailViewModel = DetailViewModel(detailRepository)
        detailViewModel.getDetailBook("isbn13")
        Thread.sleep(500)
        val returnBook = detailViewModel.detailBook.value

        assertNull(returnBook)
    }

    @Test
    fun testSaveAndGetDetailBookMemo() {
        val memoString = "dksnakjvnbjqikasdfhjsvbj"
        val detailRepository = spyk<DetailRepository>()
        coEvery { detailRepository.getDetailBook(any()) } returns DetailBook("title", "subTitle", "isbn13", "price", "image", "url",
            "isbn10", "isbn13", "1", "year", "rating", "desc", "price", "image", "url", Pdf("freeEBook"))

        val detailViewModel = DetailViewModel(detailRepository)
        detailViewModel.getDetailBook("isbn13")
        Thread.sleep(500)

        detailViewModel.getDetailBookMemo("isbn13")
        Thread.sleep(500)
        detailViewModel.saveDetailBookMemo(memoString)
        Thread.sleep(500)
        detailViewModel.getDetailBookMemo("isbn13")
        Thread.sleep(500)

        assertEquals(memoString, detailViewModel.detailBookMemo.value!!.memoString)
    }
}