package com.lee.book.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lee.book.R
import com.lee.book.databinding.FragmentDashboardBinding
import com.lee.book.databinding.FragmentHomeBinding
import com.lee.book.ui.dashboard.DashboardViewModel

class HomeFragment : Fragment() {
    private var _fragmentHomeBinding : FragmentHomeBinding? = null
    private val fragmentHomeBinding get() = _fragmentHomeBinding!!

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val textView: TextView = fragmentHomeBinding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return fragmentHomeBinding.root
    }

    override fun onDestroyView() {
        _fragmentHomeBinding = null
        super.onDestroyView()
    }
}