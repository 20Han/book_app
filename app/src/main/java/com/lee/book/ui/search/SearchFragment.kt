package com.lee.book.ui.search

import android.os.Bundle
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

        fragmentSearchBinding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query != null) {
                    if(this@SearchFragment.query != query) {
                        searchBookList.clear()
                        this@SearchFragment.query = query
                        this@SearchFragment.page = 1
                    }
                    searchViewModel.searchBooks(query, "1", context)
                }
                else
                    Toast.makeText(context, "query is empty", Toast.LENGTH_SHORT).show()

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
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

        fragmentSearchBinding.searchRecyclerView.addOnScrollListener(object : OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!recyclerView.canScrollVertically(1) && dy > 0) {
                    if(this@SearchFragment.page == searchViewModel.page) {
                        this@SearchFragment.page += 1
                        searchViewModel.searchBooks(query, page.toString(), context)
                    }else{
                        Toast.makeText(context, "end of search result", Toast.LENGTH_LONG).show()
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
}