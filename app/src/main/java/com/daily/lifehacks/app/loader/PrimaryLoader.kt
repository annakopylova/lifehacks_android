package com.daily.lifehacks.app.loader

import com.daily.database.DatabaseAPI
import com.daily.database.entity.Article
import com.daily.lifehacks.app.loader.article.ArticleLoader
import com.daily.lifehacks.app.loader.article.ArticleLoaderImpl
import com.daily.lifehacks.app.loader.comment.CommentLoader
import com.daily.lifehacks.app.loader.comment.CommentLoaderImpl
import com.daily.lifehacks.app.loader.section.SectionLoader
import com.daily.lifehacks.app.loader.section.SectionLoaderImpl
import com.daily.lifehacks.app.receiver.ArticleReceiver
import com.daily.network.NetworkGate
import javax.inject.Inject

class PrimaryLoader (
    databaseAPI: DatabaseAPI,
    networkGate: NetworkGate
) {

    val articleLoader: ArticleLoader =
        ArticleLoaderImpl(databaseAPI, networkGate)
    val commentLoader: CommentLoader =
        CommentLoaderImpl(databaseAPI, networkGate)
    val sectionLoader: SectionLoader =
        SectionLoaderImpl(databaseAPI, networkGate)

    fun getArticlesForSection(
        articleReceiver: ArticleReceiver,
        sectionGUID: String,
        offset: Long
    ) {
        articleLoader.loadArticlesBySectionGUID(sectionGUID)
    }

    fun getCommentsForArticle(articleGUID: String) {

    }

    fun getSections() {

    }
}