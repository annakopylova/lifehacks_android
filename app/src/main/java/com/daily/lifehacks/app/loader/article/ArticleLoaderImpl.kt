package com.daily.lifehacks.app.loader.article

import android.os.Handler
import android.os.Looper
import com.daily.database.DatabaseAPI
import com.daily.network.NetworkGate

class ArticleLoaderImpl(
    databaseAPI: DatabaseAPI,
    networkGate: NetworkGate):
    ArticleLoader {
    override fun loadArticleByGUID(articleGUID: String) {
        TODO("Not yet implemented")
    }

    override fun loadArticlesBySectionGUID(sectionGUID: String) {
        TODO("Not yet implemented")
    }

}