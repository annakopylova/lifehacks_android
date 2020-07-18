package com.daily.network.controller.comment

import com.daily.network.Api
import retrofit2.Callback

interface CommentController  {
    fun loadComments(articleGUID: String, token: String, userGUID: String, callback: Callback<CommentResponse>)
    fun leaveComment(articleGUID: String, message: String, token: String, userGUID: String, callback: Callback<CommentResponse>)
}