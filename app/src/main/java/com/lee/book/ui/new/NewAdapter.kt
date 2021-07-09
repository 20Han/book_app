package com.lee.book.ui.new

import android.os.Bundle
import android.text.TextUtils.replace
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

class NewAdapter(
    private val newBookList : ArrayList<Book>,
    private val fragmentManager : FragmentManager
) : RecyclerView.Adapter<NewAdapter.NewViewHolder>(){

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

        holder.itemView.setOnClickListener {
            fragmentManager.commit {
                val detailFragment = DetailFragment()
                val bundle = Bundle()
                bundle.putString("isbn13", newBookList[position].isbn13)
                detailFragment.arguments = bundle
                replace(R.id.nav_host_fragment, detailFragment)
                addToBackStack("detail_fragment")
            }
        }
    }

    override fun getItemCount() = newBookList.size
}