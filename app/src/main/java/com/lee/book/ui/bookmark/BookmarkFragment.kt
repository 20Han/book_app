package com.lee.book.ui.bookmark

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lee.book.databinding.FragmentBookmarkBinding
import com.lee.book.entitiy.Book
import java.util.*
import kotlin.collections.ArrayList

class BookmarkFragment : Fragment() {
    private var _fragmentBookmarkBinding: FragmentBookmarkBinding? = null
    private val fragmentBookmarkBinding get() = _fragmentBookmarkBinding!!
    private val bookmarkViewModel: BookmarkViewModel by activityViewModels()
    private var bookmarkList = ArrayList<Book>()
    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END, 0) {
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            val fromPosition = viewHolder.adapterPosition
            val toPosition = target.adapterPosition

            Collections.swap(bookmarkViewModel.bookMarkedBooks.value!!, fromPosition, toPosition)

            recyclerView.adapter?.notifyItemMoved(fromPosition, toPosition)

            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            Log.d("ItemTouchHelperTest", "onSwiped")
        }
    })

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _fragmentBookmarkBinding = FragmentBookmarkBinding.inflate(inflater)
        fragmentBookmarkBinding.bookmarkRecyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = BookmarkAdapter(bookmarkList)
        fragmentBookmarkBinding.bookmarkRecyclerView.adapter = adapter

        bookmarkViewModel.bookMarkedBooks.observe(viewLifecycleOwner, {
            bookmarkList.clear()
            bookmarkList.addAll(it)
            adapter.notifyDataSetChanged()
        })

        itemTouchHelper.attachToRecyclerView(fragmentBookmarkBinding.bookmarkRecyclerView)

        return fragmentBookmarkBinding.root
    }

    override fun onDestroyView() {
        _fragmentBookmarkBinding = null
        super.onDestroyView()
    }
}