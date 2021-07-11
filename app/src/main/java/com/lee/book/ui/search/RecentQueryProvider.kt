package com.lee.book.ui.search

import android.content.SearchRecentSuggestionsProvider

class RecentQueryProvider : SearchRecentSuggestionsProvider(){
//    val authority = "com.lee.book.ui.search.RecentQueryProvider"
//    val mode = DATABASE_MODE_QUERIES

    companion object{
        const val AUTHORITY = "com.lee.book.ui.search.RecentQueryProvider"
        const val MODE = DATABASE_MODE_QUERIES
    }

    init {
        setupSuggestions(AUTHORITY, MODE)
    }
}