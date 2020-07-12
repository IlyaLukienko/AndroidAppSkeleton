package com.lukienko.androidappskeleton.presentation.characterList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lukienko.androidappskeleton.data.entity.Character
import com.lukienko.androidappskeleton.domain.interactor.CharacterInteractor
import io.reactivex.disposables.CompositeDisposable

class CharacterListViewModel(private val interactor: CharacterInteractor) : ViewModel() {
    private val disposable: CompositeDisposable = CompositeDisposable()
    val errorMessageVisible = MutableLiveData<Boolean>()
    val loadingProgressVisible = MutableLiveData<Boolean>()
    val characters = MutableLiveData<List<Character>>()
    var isListSorted = false

    init {
        loadCharacters()
    }

    fun loadCharacters() {
        loadingProgressVisible.postValue(true)
        errorMessageVisible.postValue(false)
        disposable.add(
            interactor
                .loadCharacters()
                .subscribe({
                    characters.postValue(it)
                    loadingProgressVisible.postValue(false)
                }, {
                    errorMessageVisible.postValue(true)
                    loadingProgressVisible.postValue(false)
                })
        )
    }

    fun sortCharacters() {
        characters.value?.let {
            val sortedList = interactor.getSortedCharactersByDate(it, isListSorted)
            characters.postValue(sortedList)
            isListSorted = !isListSorted
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}