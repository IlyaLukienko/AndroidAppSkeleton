package com.lukienko.androidappskeleton.domain.repository

import com.lukienko.androidappskeleton.data.entity.Character
import com.lukienko.androidappskeleton.data.entity.Location
import io.reactivex.Observable
import io.reactivex.Single

interface ICharacterRepository {
    fun getCharacters(): Single<List<Character>>
    fun getLocation(id: Int): Single<Location>
    fun getCharacter(id: Int): Observable<Character>
}