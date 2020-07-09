package com.lukienko.androidappskeleton.presentation.characterList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lukienko.androidappskeleton.data.Character
import com.lukienko.androidappskeleton.domain.useCase.GetCharactersUseCase
import io.reactivex.disposables.CompositeDisposable
import org.koin.core.KoinComponent
import org.koin.core.inject

class CharacterListViewModel : ViewModel(), KoinComponent {
    private val getCharactersUseCase: GetCharactersUseCase by inject()

    val disposable: CompositeDisposable = CompositeDisposable()
    val errorMessage = MutableLiveData<String>()
    val loadingProgress = MutableLiveData<Boolean>()
    val characters = MutableLiveData<List<Character>>()

    init {
        loadCharacters()
    }

    private fun loadCharacters() {
        loadingProgress.postValue(true)
        disposable.add(
            getCharactersUseCase
                .execute()
                .subscribe({
                    characters.postValue(it)
                    loadingProgress.postValue(false)
                }, {
                    errorMessage.postValue(it.message)
                    loadingProgress.postValue(false)
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}