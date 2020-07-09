package com.lukienko.androidappskeleton.presentation.characterDetails

import android.os.Bundle
import com.lukienko.androidappskeleton.R
import com.lukienko.androidappskeleton.presentation.BaseFragment

private const val ARG_NAME = "name"

class CharacterDetailsFragment : BaseFragment() {
    companion object {
        fun newInstance(name: String) =
            CharacterDetailsFragment()
                .apply {
                    arguments = Bundle().apply {
                        putString(ARG_NAME, name)
                    }
                }
    }

    override fun getFragmentLayout(): Int {
        return R.layout.fragment_character_details
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}