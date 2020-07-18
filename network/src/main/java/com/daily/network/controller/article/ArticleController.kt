package com.daily.network.controller.article

import com.daily.network.Api
import retrofit2.Callback
import retrofit2.http.Query
import java.io.File

interface ArticleController {
    fun loadArticleById(GUID: String, token: String, userGUID: String, callback: Callback<ArticleResponse>)
    fun loadArticleById(GUID: List<String>, token: String, userGUID: String, callback: Callback<MultipleArticleResponse>)
    fun loadArticles(sectionGUID: String, token: String, userGUID: String, callback: Callback<MultipleArticleResponse>)
    fun addArticle(title: String,
                  description: String,
                  category_key: String,
                  userGUID: String,
                  token: String,
                  docfile: File, callback: Callback<ArticleResponse>)
}