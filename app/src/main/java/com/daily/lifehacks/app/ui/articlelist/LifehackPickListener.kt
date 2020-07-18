package com.daily.lifehacks.app.ui.articlelist

import com.daily.database.entity.Article
import com.daily.database.entity.Section

interface LifehackPickListener {
    fun onArticleClicked(article: Article)
    fun onArticleLikeClicked(article: Article, position: Int)
    fun onArticleSaveClicked(article: Article, position: Int)
    fun getArticleSection(article: Article): Section
}