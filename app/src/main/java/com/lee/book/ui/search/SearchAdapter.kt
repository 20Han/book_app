package com.lee.book.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lee.book.databinding.ItemBookBinding
import com.lee.book.entitiy.Book
import com.lee.book.ui.new.NewFragmentDirections

class SearchAdapter(
    private val searchBookList: ArrayList<Book>
) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>(){

    class SearchViewHolder(view : View, itemBookBinding: ItemBookBinding ): RecyclerView.ViewHolder(view) {
        val image  = itemBookBinding.newBookImage
        val title  = itemBookBinding.newBookTitle
        val subTitle  = itemBookBinding.newBookSubtitle
        val url  = itemBookBinding.newBookUrl
        val isbn3  = itemBookBinding.newBookIsbn3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val itemSearchViewHolder = ItemBookBinding.inflate(LayoutInflater.from(parent.context))
        val view = itemSearchViewHolder.root

        return SearchViewHolder(view, itemSearchViewHolder)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.title.text = searchBookList[position].title
        holder.subTitle.text = searchBookList[position].subtitle
        holder.url.text = searchBookList[position].url
        holder.isbn3.text = searchBookList[position].isbn13
        Glide.with(holder.itemView).load(searchBookList[position].image).into(holder.image)

        holder.itemView.setOnClickListener {
            val isbn13 = searchBookList[position].isbn13
            val action = SearchFragmentDirections.actionNavigationSearchToNavigationDetail22(isbn13)
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount() = searchBookList.size
}