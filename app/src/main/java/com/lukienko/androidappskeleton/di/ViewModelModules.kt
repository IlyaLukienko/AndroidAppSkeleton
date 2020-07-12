package com.lukienko.androidappskeleton.di

import com.lukienko.androidappskeleton.presentation.characterDetails.CharacterDetailsViewModel
import com.lukienko.androidappskeleton.presentation.characterList.CharacterListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module(override = true) {
    viewModel { CharacterListViewModel(get()) }
    viewModel { CharacterDetailsViewModel() }
}