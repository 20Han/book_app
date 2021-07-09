package com.lee.book.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lee.book.databinding.FragmentNewBinding

class NewFragment : Fragment() {
    private var _fragmentNewBinding : FragmentNewBinding? = null
    private val fragmentNewBinding get() = _fragmentNewBinding!!

    private val newViewModel: NewViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _fragmentNewBinding = FragmentNewBinding.inflate(inflater)

        val textView: TextView = fragmentNewBinding.textNew

        newViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        newViewModel.getNewBooks()

        return fragmentNewBinding.root
    }

    override fun onDestroyView() {
        _fragmentNewBinding = null
        super.onDestroyView()
    }
}