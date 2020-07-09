package com.lukienko.androidappskeleton.presentation.characterList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lukienko.androidappskeleton.data.Character
import com.lukienko.androidappskeleton.domain.interactor.CharacterInteractor
import io.reactivex.disposables.CompositeDisposable
import org.koin.core.KoinComponent
import org.koin.core.inject

class CharacterListViewModel : ViewModel(), KoinComponent {
    private val interactor: CharacterInteractor by inject()
    val disposable: CompositeDisposable = CompositeDisposable()
    val errorMessage = MutableLiveData<String>()
    val loadingProgress = MutableLiveData<Boolean>()
    val characters = MutableLiveData<List<Character>>()
    var isListSorted = false

    init {
        loadCharacters()
    }

    private fun loadCharacters() {
        loadingProgress.postValue(true)
        disposable.add(
            interactor
                .loadCharacters()
                .subscribe({
                    characters.postValue(it)
                    loadingProgress.postValue(false)
                }, {
                    errorMessage.postValue(it.message)
                    loadingProgress.postValue(false)
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