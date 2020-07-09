package com.lukienko.androidappskeleton.domain.interactor

import com.lukienko.androidappskeleton.data.Character
import com.lukienko.androidappskeleton.domain.repository.ICharacterRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject

class CharacterInteractor : KoinComponent {

    private val characterRepository: ICharacterRepository by inject()

    fun loadCharacters(): Single<List<Character>> {
        return characterRepository
            .getCharacters()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getSortedCharactersByDate(characters: List<Character>, isSorted: Boolean): List<Character> {
        return if (isSorted) characters.sortedBy { it.created }
        else characters.sortedByDescending { it.created }
    }
}