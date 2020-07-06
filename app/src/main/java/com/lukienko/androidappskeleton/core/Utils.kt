package com.lukienko.androidappskeleton.core

import com.lukienko.androidappskeleton.data.CharacterResult
import retrofit2.Response

class Utils {
    companion object {
        fun getName(characterResponse: Response<CharacterResult>): List<String> {
            val names = mutableListOf<String>()
            for (ch in characterResponse.body()!!.results) {
                names.add(ch.name)
            }

            return names
        }
    }
}