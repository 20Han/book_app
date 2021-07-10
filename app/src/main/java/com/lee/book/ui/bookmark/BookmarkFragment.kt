package com.lee.book.ui.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.lee.book.databinding.FragmentBookmarkBinding
import com.lee.book.databinding.FragmentDetailBinding
import com.lee.book.entitiy.Book

class BookmarkFragment : Fragment() {
    private var _fragmentBookmarkBinding: FragmentBookmarkBinding? = null
    private val fragmentBookmarkBinding get() = _fragmentBookmarkBinding!!
    private val bookmarkViewModel : BookmarkViewModel by activityViewModels()
    private var bookmarkList = ArrayList<Book>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _fragmentBookmarkBinding = FragmentBookmarkBinding.inflate(inflater)
        fragmentBookmarkBinding.bookmarkRecyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = BookmarkAdapter(bookmarkList, parentFragmentManager)
        fragmentBookmarkBinding.bookmarkRecyclerView.adapter = adapter

        bookmarkViewModel.bookMarkedBooks.observe(viewLifecycleOwner, {
            bookmarkList.clear()
            bookmarkList.addAll(it)
            adapter.notifyDataSetChanged()
        })

        return fragmentBookmarkBinding.root
    }

    override fun onDestroyView() {
        _fragmentBookmarkBinding = null
        super.onDestroyView()
    }
}