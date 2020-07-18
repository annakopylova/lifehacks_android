package com.daily.network.controller.article

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.daily.network.Api
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Callback
import java.io.ByteArrayOutputStream
import java.io.File


class ArticleControllerImpl(val api: Api) : ArticleController {
    override fun loadArticleById(
        GUID: String,
        token: String,
        userGUID: String,
        callback: Callback<ArticleResponse>
    ) {
        val call = api.loadArticleById(GUID, userGUID, token)
        call.enqueue(callback)
    }

    override fun loadArticleById(
        GUID: List<String>,
        token: String,
        userGUID: String,
        callback: Callback<MultipleArticleResponse>
    ) {
        val call = api.loadArticleById(GUID, userGUID, token)
        call.enqueue(callback)
    }

    override fun loadArticles(
        sectionGUID: String,
        token: String,
        userGUID: String,
        callback: Callback<MultipleArticleResponse>
    ) {
        val call = api.loadArticles(sectionGUID, userGUID, token)
        call.enqueue(callback)
    }

    override fun addArticle(
        title: String,
        description: String,
        category_key: String,
        userGUID: String,
        token: String,
        docfile: File,
        callback: Callback<ArticleResponse>
    ) {

        val rbody = RequestBody.create(MediaType.parse("image/jpeg"), docfile)
        val filebody = MultipartBody.Part.createFormData("docfile", docfile.name, rbody)
        val call = api.addArticle(
            toRequestBody(title), toRequestBody(description),
            toRequestBody(category_key), toRequestBody(userGUID), toRequestBody(token), filebody
        )
        call.enqueue(callback)
    }

    fun toRequestBody(value: String): RequestBody {
        return RequestBody.create(MediaType.parse("text/plain"), value)
    }
}