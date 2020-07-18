package com.daily.network.controller.favourite

import com.daily.network.Api
import retrofit2.Call
import retrofit2.Callback

class FavouriteControllerImpl(val api: Api) : FavouriteController {
    override fun getFavourite(userGUID: String, token: String, callback: Callback<FavouriteResponse>) {
        val call = api.loadFavourite(userGUID, token)
        call.enqueue(callback)
    }

    override fun addFavourite(
        articleGUID: String,
        userGUID: String,
        token: String,
        status: Boolean,
        callback: Callback<FavouriteResponse>
    ) {
        val call = api.addFavourite(articleGUID, userGUID, token, status)
        call.enqueue(callback)
    }
}