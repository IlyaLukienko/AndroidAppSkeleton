package com.lukienko.androidappskeleton.data.repository

import com.lukienko.androidappskeleton.data.Character
import com.lukienko.androidappskeleton.data.RetrofitProvider
import com.lukienko.androidappskeleton.domain.repository.ICharacterRepository
import io.reactivex.Single
import org.koin.core.KoinComponent
import org.koin.core.inject

class CharacterRepository : ICharacterRepository, KoinComponent {
    private val retrofitProvider: RetrofitProvider by inject()

    override fun getCharacters(): Single<List<Character>> {
        return retrofitProvider
            .getApiService()
            .getCharacters()
            .map { it.body()?.results }
    }
}