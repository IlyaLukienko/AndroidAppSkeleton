package com.lukienko.androidappskeleton.data.entity

data class Character(
    val id: Int,
    val name: String,
    var image: String? = null,
    var status: String? = null,
    var species: String? = null,
    var gender: String? = null,
    var created: String? = null,
    var location: Location
)