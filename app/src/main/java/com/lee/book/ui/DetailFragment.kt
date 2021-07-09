package com.lee.book.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lee.book.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private var _fragmentDetailBinding: FragmentDetailBinding? = null
    private val fragmentDetailBinding get() = _fragmentDetailBinding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View?{
        _fragmentDetailBinding = FragmentDetailBinding.inflate(inflater)

        return fragmentDetailBinding.root
    }

    override fun onDestroyView() {
        _fragmentDetailBinding = null
        super.onDestroyView()
    }
}