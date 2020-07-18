package com.daily.network.controller.like

import com.daily.network.Api
import retrofit2.Callback

interface LikeController {
    fun likeArticle(articleGUID: String, token: String, userGUID: String, status: Boolean, callback: Callback<LikeResponse>)
}