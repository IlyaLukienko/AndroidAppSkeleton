package com.lukienko.androidappskeleton.presentation.characterDetails

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import com.google.android.material.snackbar.Snackbar
import com.lukienko.androidappskeleton.R
import com.lukienko.androidappskeleton.data.entity.Character
import com.lukienko.androidappskeleton.presentation.BaseFragment
import com.lukienko.androidappskeleton.presentation.utils.ImageLoader
import kotlinx.android.synthetic.main.fragment_character_details.*
import org.koin.core.KoinComponent
import org.koin.core.inject

class CharacterDetailsFragment : BaseFragment(), KoinComponent {
    private val imageLoader: ImageLoader by inject()
    private val viewModel: CharacterDetailsViewModel by inject()
    private val args: CharacterDetailsFragmentArgs by navArgs()

    override fun getFragmentLayout(): Int {
        return R.layout.fragment_character_details
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        val character = args.character
        viewModel.loadResidents(character.id)
        showName(character.name)
        showPhoto(character.image, character.id.toString())
        showLocationName(character.location?.name)
        bindViews()
        bindViewsActions()
    }

    private fun bindViews() {
        viewModel.loadingProgressVisible.observe(
            viewLifecycleOwner,
            Observer { loadingVisibility(it) })
        viewModel.errorMessageVisible.observe(
            viewLifecycleOwner,
            Observer { if (it) showSnackBarError() })
        viewModel.residents.observe(viewLifecycleOwner, Observer { initList(it) })
    }

    private fun initList(list: List<Character>) {
        val adapter = ResidentListAdapter(list)
        rvLocationResidents.layoutManager = LinearLayoutManager(context)
        rvLocationResidents.adapter = adapter
    }

    private fun showName(name: String) {
        tvCharacterName.text = name
    }

    private fun showPhoto(url: String?, id: String) {
        ivPhoto.transitionName = id
        url?.let { imageLoader.loadImage(ivPhoto, it) }
    }

    private fun showLocationName(locationName: String?) {
        locationName?.let { tvLocationName.text = it }
    }

    private fun loadingVisibility(isVisible: Boolean) {
        if (isVisible) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    private fun showSnackBarError() {
        Snackbar
            .make(rootView, getString(R.string.unexpected_server_error), Snackbar.LENGTH_SHORT)
            .show()
    }

    private fun bindViewsActions() {
        toolbar.setNavigationOnClickListener { navController.navigateUp() }
        ivResidentSelection.setOnClickListener {
            it.isSelected = !it.isSelected
            setListVisibility(it)
        }
    }

    private fun setListVisibility(it: View) {
        if (it.isSelected) {
            rvLocationResidents.visibility = View.VISIBLE
        } else {
            rvLocationResidents.visibility = View.GONE
        }
    }
}