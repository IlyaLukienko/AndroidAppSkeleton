package com.lukienko.androidappskeleton.domain.interactor

import com.lukienko.androidappskeleton.data.entity.Character
import com.lukienko.androidappskeleton.domain.repository.ICharacterRepository
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject

class CharacterInteractor : KoinComponent {

    private val characterRepository: ICharacterRepository by inject()
    private var characterList: List<Character>? = null

    fun loadCharacters(): Single<List<Character>> {
        return characterRepository
            .getCharacters()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterSuccess { characterList = it }
    }

    fun loadCharacter(id: Int): Single<Character> {
        return characterRepository
            .getCharacter(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun loadResidents(id: Int): Single<List<Character>> {
        return characterRepository.getLocation(id)
            .subscribeOn(Schedulers.io())
            .flatMapObservable { location -> getResidentsIds(location.residents) }
            .flatMapSingle{ it -> characterRepository.getCharacter(it) }
            .toList()
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun getResidentsIds(residentUrls: List<String>): Observable<Int> {
        val ids = residentUrls.map {
            it.substringAfterLast("/").toInt()
        }
        return Observable
            .fromIterable(ids)
            .subscribeOn(Schedulers.computation())
    }

    fun getSortedCharactersByDate(characters: List<Character>, isSorted: Boolean): List<Character> {
        return if (isSorted) characters.sortedBy { it.created }
        else characters.sortedByDescending { it.created }
    }
}