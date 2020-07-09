package com.lukienko.androidappskeleton.data

import com.lukienko.androidappskeleton.data.entity.Character
import com.lukienko.androidappskeleton.data.entity.CharacterResult
import com.lukienko.androidappskeleton.data.entity.Location
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("character/")
    fun getCharacters(): Single<CharacterResult>

    @GET("character/{id}")
    fun getCharacter(@Path("id") id: Int): Observable<Character>

    @GET("location/{id}")
    fun getLocation(@Path("id") id: Int): Single<Location>
}