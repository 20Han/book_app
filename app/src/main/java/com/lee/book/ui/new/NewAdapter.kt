package com.lee.book.ui.new

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lee.book.databinding.ItemNewBookBinding
import com.lee.book.entitiy.Book

class NewAdapter(private val newBookList : ArrayList<Book>) : RecyclerView.Adapter<NewAdapter.NewViewHolder>(){

    class NewViewHolder(view : View, itemNewBookBinding: ItemNewBookBinding): RecyclerView.ViewHolder(view) {
        val image  = itemNewBookBinding.newBookImage
        val title  = itemNewBookBinding.newBookTitle
        val subTitle  = itemNewBookBinding.newBookSubtitle
        val url  = itemNewBookBinding.newBookUrl
        val isbn3  = itemNewBookBinding.newBookIsbn3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewViewHolder {
        val itemNewViewHolder = ItemNewBookBinding.inflate(LayoutInflater.from(parent.context))
        val view = itemNewViewHolder.root

        return NewViewHolder(view, itemNewViewHolder)
    }

    override fun onBindViewHolder(holder: NewViewHolder, position: Int) {
        holder.title.text = newBookList[position].title
        holder.subTitle.text = newBookList[position].subtitle
        holder.url.text = newBookList[position].url
        holder.isbn3.text = newBookList[position].isbn13
    }

    override fun getItemCount() = newBookList.size
}