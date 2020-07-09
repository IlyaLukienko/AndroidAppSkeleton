package com.lukienko.androidappskeleton.domain.useCase

import com.lukienko.androidappskeleton.data.Character
import com.lukienko.androidappskeleton.domain.repository.ICharacterRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject

class GetCharactersUseCase : KoinComponent {

    private val characterRepository: ICharacterRepository by inject()

    fun execute(): Single<List<Character>> {
        return characterRepository
            .getCharacters()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}