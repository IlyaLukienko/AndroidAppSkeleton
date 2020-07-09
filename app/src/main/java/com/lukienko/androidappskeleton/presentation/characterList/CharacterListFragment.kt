package com.lukienko.androidappskeleton.presentation.characterList

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.lukienko.androidappskeleton.R
import com.lukienko.androidappskeleton.data.Character
import com.lukienko.androidappskeleton.presentation.BaseFragment
import kotlinx.android.synthetic.main.fragment_character_list.*
import org.koin.android.ext.android.inject
import org.koin.core.KoinComponent

class CharacterListFragment : BaseFragment(), KoinComponent {

    private val viewModel: CharacterListViewModel by inject()

    override fun getFragmentLayout(): Int {
        return R.layout.fragment_character_list
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindLiveData()
        bindViewsActions()
    }

    private fun bindLiveData() {
        viewModel.loadingProgress.observe(viewLifecycleOwner, Observer { loadingVisibility(it) })
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer { })
        viewModel.characters.observe(viewLifecycleOwner, Observer { initList(it) })
    }

    private fun initList(list: List<Character>?) {
        list?.let {
            val adapter = CharactersAdapter(it)
            rvCharacters.layoutManager = LinearLayoutManager(context)
            rvCharacters.adapter = adapter
            adapter.onItemClick = {
                navController.navigate(R.id.destinationCharacterDetails)
            }
        }
    }

    private fun bindViewsActions() {
        ivFilter.setOnClickListener { viewModel.sortCharacters() }
    }
}