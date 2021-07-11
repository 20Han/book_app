package com.lee.book.ui.search

import android.app.SearchManager
import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.SearchRecentSuggestions
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.lee.book.databinding.FragmentSearchBinding
import com.lee.book.entitiy.Book


class SearchFragment : Fragment() {
    private var _fragmentSearchBinding : FragmentSearchBinding? = null
    private val fragmentSearchBinding get() = _fragmentSearchBinding!!
    private val searchViewModel: SearchViewModel by viewModels()
    private val searchBookList =  ArrayList<Book>()
    private var query = ""
    private var page = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentSearchBinding = FragmentSearchBinding.inflate(inflater)

        val searchManager = requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        fragmentSearchBinding.searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
        val suggestionsAdapter = SearchRecentAdapter(requireActivity(),null,0)
        fragmentSearchBinding.searchView.suggestionsAdapter = suggestionsAdapter

        fragmentSearchBinding.searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    if (this@SearchFragment.query != query) {
                        searchBookList.clear()
                        this@SearchFragment.query = query
                        this@SearchFragment.page = 1
                    }
                    searchViewModel.searchBooks(query, "1", context)

                    //save query to searchQueryProvider
                    val suggestions = SearchRecentSuggestions(
                        requireActivity(),
                        RecentQueryProvider.AUTHORITY,
                        RecentQueryProvider.MODE
                    )
                    suggestions.saveRecentQuery(query, null)

                } else
                    Toast.makeText(context, "query is empty", Toast.LENGTH_SHORT).show()

                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                val cursor = getRecentSuggestions(newText)
                if (cursor != null) {
                    suggestionsAdapter.swapCursor(cursor)
                }
                return false
            }
        })

        fragmentSearchBinding.searchView.setOnSuggestionListener(object : SearchView.OnSuggestionListener {
            override fun onSuggestionSelect(position: Int): Boolean {
                return false
            }

            override fun onSuggestionClick(position: Int): Boolean {
                // On Clicking Suggestion Load It To Submit Query Listener
                fragmentSearchBinding.searchView.setQuery(suggestionsAdapter.getSuggestionText(position), true)
                return true
            }
        })

        fragmentSearchBinding.searchRecyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = SearchAdapter(searchBookList)
        fragmentSearchBinding.searchRecyclerView.adapter = adapter

        searchViewModel.searchBookList.observe(viewLifecycleOwner, {
            searchBookList.addAll(it)
            adapter.notifyDataSetChanged()
        })

        // scroll to download net page
        fragmentSearchBinding.searchRecyclerView.addOnScrollListener(object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!recyclerView.canScrollVertically(1) && dy > 0) {
                    if (this@SearchFragment.page == searchViewModel.page) {
                        this@SearchFragment.page += 1
                        searchViewModel.searchBooks(query, page.toString(), context)
                    } else if (!searchViewModel.isSearching.get()) {
                        Toast.makeText(context, "end of search result", Toast.LENGTH_LONG).show()
                    } else{
                        Toast.makeText(context, "Loading...", Toast.LENGTH_LONG).show()
                    }
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })

        return fragmentSearchBinding.root
    }

    override fun onDestroyView() {
        _fragmentSearchBinding = null
        super.onDestroyView()
    }

    private fun getRecentSuggestions(query : String): Cursor? {
        val uriBuilder = Uri.Builder()
            .scheme(ContentResolver.SCHEME_CONTENT)
            .authority(RecentQueryProvider.AUTHORITY)

        uriBuilder.appendPath(SearchManager.SUGGEST_URI_PATH_QUERY)

        val uri = uriBuilder.build()
        val selection = " ?"
        val selArgs = arrayOf(query)

        return activity?.contentResolver?.query(uri, null, selection, selArgs, null)
    }
}
