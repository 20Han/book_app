package com.lee.book.entitiy

data class Book(
    var title: String,
    var subTitle: String,
    var isbn13: String,
    var price: String,
    var image: String,
    var url: String
)

data class NewBooks(
    var error : String,
    var total : String,
    val books : List<Book>
)

data class SearchBooks(
    var error : String,
    var total : String,
    var page : String,
    var books : List<Book>
)

data class DetailBook(
    var error : String,
    var title : String,
    var subtitle : String,
    var authors : String,
    var publisher : String,
    var language : String,
    var isbn10 : String,
    var isbn13 : String,
    var pages : String,
    var year : String,
    var rating : String,
    var desc : String,
    var price : String,
    var image : String
)