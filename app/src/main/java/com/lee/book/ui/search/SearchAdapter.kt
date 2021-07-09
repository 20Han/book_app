package com.lee.book.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lee.book.R
import com.lee.book.databinding.ItemBookBinding
import com.lee.book.entitiy.Book
import com.lee.book.ui.Detail.DetailFragment

class SearchAdapter(
        private val searchBookList : ArrayList<Book>,
        private val fragmentManager : FragmentManager
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
            fragmentManager.commit {
                val detailFragment = DetailFragment()
                val bundle = Bundle()
                bundle.putString("isbn13", searchBookList[position].isbn13)
                detailFragment.arguments = bundle
                replace(R.id.nav_host_fragment, detailFragment)
                addToBackStack("detail_fragment")
            }
        }
    }

    override fun getItemCount() = searchBookList.size
}