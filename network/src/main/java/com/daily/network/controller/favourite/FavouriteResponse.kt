package com.daily.network.controller.favourite

data class FavouriteResponse(
    val success: Boolean,
    val data: FavouriteResponseData
)

data class FavouriteResponseData (
    val article_id: String,
    val favourite: Boolean
)
