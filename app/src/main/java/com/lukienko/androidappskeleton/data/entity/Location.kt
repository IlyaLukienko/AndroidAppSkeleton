package com.lukienko.androidappskeleton.data.entity

data class Location(
    val id: Int,
    val name: String,
    var type: String? = null,
    val dimension: String? = null,
    val residents: List<String>? = null,
    val url: String? = null,
    val created: String? = null
)