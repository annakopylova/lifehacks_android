package com.daily.lifehacks.app.loader.comment

interface CommentLoader {
    fun loadCommentsByArticleGUID(articleGUID: String)
}