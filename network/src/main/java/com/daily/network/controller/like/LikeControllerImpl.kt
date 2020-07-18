package com.daily.network.controller.like

import com.daily.network.Api
import retrofit2.Call
import retrofit2.Callback

class LikeControllerImpl(val api: Api) : LikeController {
    override fun likeArticle(articleGUID: String, token: String, userGUID: String, status: Boolean, callback: Callback<LikeResponse>) {
        val call = api.addLike(articleGUID, userGUID, token, status)
        call.enqueue(callback)
    }
}