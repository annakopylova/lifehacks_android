package com.daily.network.controller.articlemap

import com.daily.network.Api
import retrofit2.Call
import retrofit2.Callback

class ArticleMapControllerImpl(val api: Api) : ArticleMapController {
    override fun loadArticleMap(
        count: Long,
        offset: Long,
        token: String,
        userGUID: String, callback: Callback<ArticleMapResponse>
    ) {
//        val call = api.loadArticleMap(sectionGUID, userGUID, token)
//        call.enqueue(callback)
    }

    override fun loadArticleMap(
        sectionGUID: String,
        count: Long,
        offset: Long,
        token: String,
        userGUID: String, callback: Callback<ArticleMapResponse>
    ) {
        val call = api.loadArticleMap(sectionGUID, userGUID, token)
        call.enqueue(callback)
    }

}