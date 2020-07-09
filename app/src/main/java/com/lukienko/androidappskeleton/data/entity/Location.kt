package com.lukienko.androidappskeleton.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(
    val id: Int,
    val name: String,
    val type: String?,
    val dimension: String?,
    val residents: List<String>,
    val url: String?,
    val created: String?
): Parcelable