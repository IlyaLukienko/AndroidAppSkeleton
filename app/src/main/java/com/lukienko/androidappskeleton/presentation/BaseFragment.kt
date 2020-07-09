package com.lukienko.androidappskeleton.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

abstract class BaseFragment : Fragment() {
    private lateinit var loadingProgress: ProgressBar
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getFragmentLayout(), container, false)
    }

    abstract fun getFragmentLayout(): Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let { loadingProgress = ProgressBar(requireActivity()) }
        setupNavigation()
    }

    private fun setupNavigation() {
        navController = NavHostFragment.findNavController(this)
    }

    fun loadingVisibility(isVisible: Boolean) {
       if(isVisible) {
           loadingProgress.visibility = VISIBLE
       } else {
           loadingProgress.visibility = GONE
       }
    }
}