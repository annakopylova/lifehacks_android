package com.daily.lifehacks.app.receiver

import com.daily.database.entity.Article

interface ArticleReceiver {
    fun onArticlesDelivered(articles: List<Article>)
}