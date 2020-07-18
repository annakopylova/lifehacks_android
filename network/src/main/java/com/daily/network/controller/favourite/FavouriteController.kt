package com.daily.network.controller.favourite

import com.daily.network.Api
import retrofit2.Callback

interface FavouriteController {
    fun getFavourite(userGUID: String, token: String, callback: Callback<FavouriteResponse>)
    fun addFavourite(articleGUID: String, userGUID: String, token: String, status: Boolean, callback: Callback<FavouriteResponse>)
}