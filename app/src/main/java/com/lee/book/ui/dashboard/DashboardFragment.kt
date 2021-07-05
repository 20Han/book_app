package com.lee.book.ui.dashboard

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

class DashboardFragment : Fragment() {
    private var _fragmentDashboardBinding : FragmentDashboardBinding? = null
    private val fragmentDashboardBinding get() = _fragmentDashboardBinding!!
    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProvider(this).get(DashboardViewModel::class.java)
        val textView: TextView = fragmentDashboardBinding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return fragmentDashboardBinding.root
    }

    override fun onDestroyView() {
        _fragmentDashboardBinding = null
        super.onDestroyView()
    }
}