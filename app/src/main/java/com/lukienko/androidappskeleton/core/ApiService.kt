package com.lukienko.androidappskeleton.core

import com.lukienko.androidappskeleton.data.CharacterResult
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("character/")
    fun getCharacters(): Single<Response<CharacterResult>>
}