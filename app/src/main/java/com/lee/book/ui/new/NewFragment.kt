package com.lee.book.ui.new

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.lee.book.databinding.FragmentNewBinding
import com.lee.book.entitiy.Book

class NewFragment : Fragment() {
    private var _fragmentNewBinding : FragmentNewBinding? = null
    private val fragmentNewBinding get() = _fragmentNewBinding!!
    private var newBooks : ArrayList<Book> = ArrayList()
    private val newViewModel: NewViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _fragmentNewBinding = FragmentNewBinding.inflate(inflater)

        val newRecyclerView = fragmentNewBinding.newRecyclerView
        newRecyclerView.layoutManager = LinearLayoutManager(context)
        val newAdapter = NewAdapter(newBooks)
        newRecyclerView.adapter = newAdapter

        newViewModel.bookList.observe(viewLifecycleOwner, Observer {
            newBooks.clear()
            newBooks.addAll(it)
            newAdapter.notifyDataSetChanged()
        })

        newViewModel.getNewBooks()

        return fragmentNewBinding.root
    }

    override fun onDestroyView() {
        _fragmentNewBinding = null
        super.onDestroyView()
    }
}