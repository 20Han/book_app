package com.lee.book.ui.bookmark

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lee.book.databinding.ItemBookBinding
import com.lee.book.entitiy.Book

class BookmarkAdapter(
    private val bookmarkList: ArrayList<Book>
) : RecyclerView.Adapter<BookmarkAdapter.BookmarkViewHolder>(){

    class BookmarkViewHolder(view : View, itemBookBinding: ItemBookBinding): RecyclerView.ViewHolder(view) {
        val image  = itemBookBinding.newBookImage
        val title  = itemBookBinding.newBookTitle
        val subTitle  = itemBookBinding.newBookSubtitle
        val url  = itemBookBinding.newBookUrl
        val isbn3  = itemBookBinding.newBookIsbn3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val itemBookmarkViewHolder = ItemBookBinding.inflate(LayoutInflater.from(parent.context))
        val view = itemBookmarkViewHolder.root

        return BookmarkViewHolder(view, itemBookmarkViewHolder)
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        holder.title.text = bookmarkList[position].title
        holder.subTitle.text = bookmarkList[position].subtitle
        holder.url.text = bookmarkList[position].url
        holder.isbn3.text = bookmarkList[position].isbn13
        Glide.with(holder.itemView).load(bookmarkList[position].image).into(holder.image)

        holder.itemView.setOnClickListener {
            val isbn13 = bookmarkList[position].isbn13
            val action = BookmarkFragmentDirections.actionNavigationBookmarkToNavigationDetail3(isbn13)
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount() = bookmarkList.size
}