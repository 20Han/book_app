package com.lee.book.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.lee.book.databinding.FragmentSearchBinding
import com.lee.book.entitiy.Book

class SearchFragment : Fragment() {
    private var _fragmentSearchBinding : FragmentSearchBinding? = null
    private val fragmentSearchBinding get() = _fragmentSearchBinding!!
    private val searchViewModel: SearchViewModel by viewModels()
    private val searchBookList =  ArrayList<Book>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _fragmentSearchBinding = FragmentSearchBinding.inflate(inflater)

        fragmentSearchBinding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query != null)
                    searchViewModel.searchBooks(query, "1")
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

        searchViewModel.searchBookList.observe(viewLifecycleOwner, Observer {
            searchBookList.clear()
            searchBookList.addAll(it)
            adapter.notifyDataSetChanged()
        })

        return fragmentSearchBinding.root
    }

    override fun onDestroyView() {
        _fragmentSearchBinding = null
        super.onDestroyView()
    }
}