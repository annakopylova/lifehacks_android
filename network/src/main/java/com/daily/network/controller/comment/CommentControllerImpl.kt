package com.daily.network.controller.comment

import com.daily.network.Api
import retrofit2.Call
import retrofit2.Callback

class CommentControllerImpl(val api: Api) : CommentController{
    override fun loadComments(articleGUID: String, token: String, userGUID: String, callback: Callback<CommentResponse>) {
        val call = api.loadComment(articleGUID, userGUID, token)
        call.enqueue(callback)
    }

    override fun leaveComment(
        articleGUID: String,
        message: String,
        token: String,
        userGUID: String, callback: Callback<CommentResponse>
    ) {
        val call = api.leaveComment(articleGUID, message, userGUID, token)
        call.enqueue(callback)
    }

}