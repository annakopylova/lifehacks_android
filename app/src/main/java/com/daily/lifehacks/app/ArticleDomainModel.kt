package com.daily.lifehacks.app

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArticleDomainModel(
    val id: Long,
    val title: String,
    var description: String,
    val picturePath: String,
    val userName: String,
    val userId: Long,
    val likeCount: Long,
    val commentCount: Long,
    val saved: Boolean,
    val category: Long,
    val date: Long) : Parcelable