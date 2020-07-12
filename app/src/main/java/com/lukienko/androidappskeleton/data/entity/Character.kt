package com.lukienko.androidappskeleton.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Character(
    val id: Int,
    val name: String,
    val image: String?,
    val status: String?,
    val species: String?,
    val gender: String?,
    val created: String?,
    val location: Location
): Parcelable