package com.lukienko.androidappskeleton.domain.repository

import com.lukienko.androidappskeleton.data.Character
import io.reactivex.Single

interface ICharacterRepository {
    fun getCharacters(): Single<List<Character>>
}