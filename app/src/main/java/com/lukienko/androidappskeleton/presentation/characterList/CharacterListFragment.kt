package com.lukienko.androidappskeleton.presentation.characterList

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.lukienko.androidappskeleton.R
import com.lukienko.androidappskeleton.data.entity.Character
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
        viewModel.loadingProgressVisible.observe(
            viewLifecycleOwner,
            Observer { loadingVisibility(it) })
        viewModel.errorMessageVisible.observe(
            viewLifecycleOwner,
            Observer { if (it) showSnackBarError() })
        viewModel.characters.observe(viewLifecycleOwner, Observer { initList(it) })
    }

    private fun initList(list: List<Character>) {
        val adapter = CharactersAdapter(list)
        rvCharacters.layoutManager = LinearLayoutManager(context)
        rvCharacters.adapter = adapter
        adapter.onItemClick = { id, imageView ->
            navigateToDetailsScreen(imageView, id)
        }
    }

    private fun navigateToDetailsScreen(
        imageView: ImageView,
        id: Int
    ) {
        val extras = FragmentNavigatorExtras(
            imageView to id.toString()
        )
        val action = CharacterListFragmentDirections.navToItemDetailFragment(
            id = id
        )
        navController.navigate(action, extras)
    }

    private fun loadingVisibility(isVisible: Boolean) {
        if(isVisible) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    private fun showSnackBarError(){
        Snackbar.make(rootView, getString(R.string.unexpected_server_error), Snackbar.LENGTH_SHORT).show()
    }

    private fun bindViewsActions() {
        ivFilter.setOnClickListener { viewModel.sortCharacters() }
    }
}