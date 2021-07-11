package com.lee.book.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.lee.book.databinding.FragmentDetailBinding
import com.lee.book.entitiy.Book
import com.lee.book.entitiy.DetailBook
import com.lee.book.ui.bookmark.BookmarkViewModel

class DetailFragment : Fragment() {
    private var _fragmentDetailBinding: FragmentDetailBinding? = null
    private val fragmentDetailBinding get() = _fragmentDetailBinding!!
    private val bookmarkViewModel : BookmarkViewModel by activityViewModels()
    private val detailViewModel : DetailViewModel by viewModels()
    private val args : DetailFragmentArgs by navArgs()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View?{
        _fragmentDetailBinding = FragmentDetailBinding.inflate(inflater)

        detailViewModel.detailBook.observe(viewLifecycleOwner, {
            setLayout(it)
        })

        detailViewModel.getDetailBook(args.isbn13)

        return fragmentDetailBinding.root
    }



    override fun onDestroyView() {
        _fragmentDetailBinding = null
        super.onDestroyView()
    }

    override fun onResume() {
        val book = detailViewModel.detailBook.value

        if(book != null)
            fragmentDetailBinding.bookMarkButton.isChecked = bookmarkViewModel.isRegisteredBookmark(Book(
                    book.title,
                    book.subtitle,
                    book.isbn13,
                    book.price,
                    book.image,
                    book.url
            ))

        super.onResume()
    }

    private fun setLayout(it : DetailBook){
        val book = Book(
                it.title,
                it.subtitle,
                it.isbn13,
                it.price,
                it.image,
                it.url
        )

        //detail view setting
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
        fragmentDetailBinding.pdf.text = it.pdf?.freeEBook
        Glide.with(this).load(it.image).into(fragmentDetailBinding.newBookImage)

        //button setting
        fragmentDetailBinding.bookMarkButton.visibility = View.VISIBLE


        fragmentDetailBinding.bookMarkButton.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                bookmarkViewModel.saveBookMark(book)
            }else{
                bookmarkViewModel.deleteBookMark(book)
            }
        }

        fragmentDetailBinding.bookMarkButton.isChecked = bookmarkViewModel.isRegisteredBookmark(book)
    }
}