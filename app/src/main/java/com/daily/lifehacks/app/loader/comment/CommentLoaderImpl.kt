package com.daily.lifehacks.app.loader.comment

import com.daily.database.DatabaseAPI
import com.daily.database.entity.Comment
import com.daily.network.NetworkGate

class CommentLoaderImpl(
    databaseAPI: DatabaseAPI,
    networkGate: NetworkGate
) : CommentLoader {
    override fun loadCommentsByArticleGUID(articleGUID: String) {
        TODO("Not yet implemented")
    }
}