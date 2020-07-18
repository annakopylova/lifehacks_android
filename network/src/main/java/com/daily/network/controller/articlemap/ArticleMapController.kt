package com.daily.network.controller.articlemap

import com.daily.network.Api
import retrofit2.Callback

interface ArticleMapController {
    fun loadArticleMap(count: Long, offset: Long, token: String, userGUID: String, callback: Callback<ArticleMapResponse>)
    fun loadArticleMap(sectionGUID: String, count: Long, offset: Long, token: String, userGUID: String, callback: Callback<ArticleMapResponse>)
}