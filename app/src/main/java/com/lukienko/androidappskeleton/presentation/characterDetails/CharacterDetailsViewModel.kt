package com.lukienko.androidappskeleton.presentation.characterDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lukienko.androidappskeleton.data.entity.Character
import com.lukienko.androidappskeleton.domain.interactor.CharacterInteractor
import io.reactivex.disposables.CompositeDisposable

class CharacterDetailsViewModel(private val interactor: CharacterInteractor) : ViewModel() {
    private val disposable: CompositeDisposable = CompositeDisposable()
    val errorMessageVisible = MutableLiveData<Boolean>()
    val loadingProgressVisible = MutableLiveData<Boolean>()
    val residents = MutableLiveData<List<Character>>()
    val character = MutableLiveData<Character>()

    fun loadData(id: Int) {
        loadingProgressVisible.postValue(true)
        errorMessageVisible.postValue(false)
        loadCharacter(id)
        loadResidents(id)
    }

    private fun loadResidents(id: Int) {
        disposable.add(
            interactor
                .loadResidents(id)
                .subscribe({
                    residents.postValue(it)
                    loadingProgressVisible.postValue(false)
                }, {
                    errorMessageVisible.postValue(true)
                    loadingProgressVisible.postValue(false)
                })
        )
    }

    private fun loadCharacter(id: Int) {
        disposable.add(
            interactor
                .loadCharacter(id)
                .subscribe({ value ->
                    value?.let { character.postValue(it) }
                }, {
                    errorMessageVisible.postValue(true)
                    loadingProgressVisible.postValue(false)
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}