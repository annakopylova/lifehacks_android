package com.daily.network.controller.article

data class MultipleArticleResponse(
    val success: Boolean,
    val data: List<ArticleJSON>
)

data class ArticleJSON(
    val article_key: String,
    val title: String,
    val description: String,
    val category_key: String,
    val creation_date: Double,
    val docfile: String,
    val like_count: Long,
    val favourite: Boolean,
    val author: String,
    val liked: Boolean
)