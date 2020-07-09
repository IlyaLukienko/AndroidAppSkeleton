package com.lukienko.androidappskeleton.data

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("character/")
    fun getCharacters(): Single<Response<CharacterResult>>
}