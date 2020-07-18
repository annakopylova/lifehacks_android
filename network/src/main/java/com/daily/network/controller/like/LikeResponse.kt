package com.daily.network.controller.like

data class LikeResponse(
    val success: Boolean,
    val data: LikeResponseData
)

data class LikeResponseData(
    val article_id: String,
    val liked: Boolean
)