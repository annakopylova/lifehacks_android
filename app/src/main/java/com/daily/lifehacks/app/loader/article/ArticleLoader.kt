package com.daily.lifehacks.app.loader.article

import android.os.Handler
import android.os.Looper

interface ArticleLoader {
    fun loadArticleByGUID(articleGUID: String)
    fun loadArticlesBySectionGUID(sectionGUID: String)
}