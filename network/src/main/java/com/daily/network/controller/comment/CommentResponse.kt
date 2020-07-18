package com.daily.network.controller.comment

data class CommentResponse(
    val success: Boolean,
    val data: List<CommentResponseSingle>
)

data class CommentResponseSingle(
    val comment_key: String,
    val user_key: String,
    val article_key: String,
    val text: String,
    val name: String,
    val creation_date: Double
)