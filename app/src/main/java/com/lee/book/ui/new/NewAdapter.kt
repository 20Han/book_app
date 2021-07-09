package com.lee.book.ui.new

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lee.book.databinding.ItemBookBinding
import com.lee.book.entitiy.Book

class NewAdapter(private val newBookList : ArrayList<Book>) : RecyclerView.Adapter<NewAdapter.NewViewHolder>(){

    class NewViewHolder(view : View, itemBookBinding: ItemBookBinding): RecyclerView.ViewHolder(view) {
        val image  = itemBookBinding.newBookImage
        val title  = itemBookBinding.newBookTitle
        val subTitle  = itemBookBinding.newBookSubtitle
        val url  = itemBookBinding.newBookUrl
        val isbn3  = itemBookBinding.newBookIsbn3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewViewHolder {
        val itemNewViewHolder = ItemBookBinding.inflate(LayoutInflater.from(parent.context))
        val view = itemNewViewHolder.root

        return NewViewHolder(view, itemNewViewHolder)
    }

    override fun onBindViewHolder(holder: NewViewHolder, position: Int) {
        holder.title.text = newBookList[position].title
        holder.subTitle.text = newBookList[position].subtitle
        holder.url.text = newBookList[position].url
        holder.isbn3.text = newBookList[position].isbn13
        Glide.with(holder.itemView).load(newBookList[position].image).into(holder.image)
    }

    override fun getItemCount() = newBookList.size
}