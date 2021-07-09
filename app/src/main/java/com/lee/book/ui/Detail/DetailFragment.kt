package com.lee.book.ui.Detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.lee.book.databinding.FragmentDetailBinding
import com.lee.book.entitiy.Book
import com.lee.book.ui.notifications.BookmarkViewModel

class DetailFragment : Fragment() {
    private var _fragmentDetailBinding: FragmentDetailBinding? = null
    private val fragmentDetailBinding get() = _fragmentDetailBinding!!
    private val bookmarkViewModel : BookmarkViewModel by activityViewModels()
    private val detailViewModel : DetailViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View?{
        _fragmentDetailBinding = FragmentDetailBinding.inflate(inflater)

        detailViewModel.detailBook.observe(viewLifecycleOwner, {
            fragmentDetailBinding.title.text = it.title
            fragmentDetailBinding.subtitle.text = it.subtitle
            fragmentDetailBinding.author.text = it.authors
            fragmentDetailBinding.publisher.text = it.publisher
            fragmentDetailBinding.language.text = it.language
            fragmentDetailBinding.isbn10.text = it.isbn10
            fragmentDetailBinding.isbn13.text = it.isbn13
            fragmentDetailBinding.pages.text = it.pages
            fragmentDetailBinding.year.text = it.year
            fragmentDetailBinding.rating.text = it.rating
            fragmentDetailBinding.desc.text = it.desc
            fragmentDetailBinding.price.text = it.price
            fragmentDetailBinding.url.text = it.url
            Glide.with(this).load(it.image).into(fragmentDetailBinding.newBookImage)
            fragmentDetailBinding.bookMarkButton.visibility = View.VISIBLE
            fragmentDetailBinding.bookMarkButton.setOnClickListener { v ->
                bookmarkViewModel.saveBookMark(Book(
                    it.title,
                    it.subtitle,
                    it.isbn13,
                    it.price,
                    it.image,
                    it.url
                ))
            }
        })

        detailViewModel.getDetailBook(arguments?.getString("isbn13"))

        return fragmentDetailBinding.root
    }

    override fun onDestroyView() {
        _fragmentDetailBinding = null
        super.onDestroyView()
    }
}