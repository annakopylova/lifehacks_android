package com.daily.network

import com.daily.network.controller.article.ArticleResponse
import com.daily.network.controller.article.MultipleArticleResponse
import com.daily.network.controller.articlemap.ArticleMapResponse
import com.daily.network.controller.auth.SignInResponse
import com.daily.network.controller.auth.SignUpResponse
import com.daily.network.controller.comment.CommentResponse
import com.daily.network.controller.favourite.FavouriteResponse
import com.daily.network.controller.like.LikeResponse
import com.daily.network.controller.section.SectionResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @POST("user/signup")
    fun signUp(
        @Query("login") login: String,
        @Query("email") email: String,
        @Query("password") password: String
    ):
            Call<SignUpResponse>

    @POST("user/signin")
    fun signIn(
        @Query("input") input: String,
        @Query("password") password: String
    ):
            Call<SignInResponse>

    @Multipart
    @POST("articles/add")
    fun addArticle(
        @Part("title") title: RequestBody,
        @Part("description") description: RequestBody,
        @Part("sectionGUID") category_key: RequestBody,
        @Part("userGUID") userGUID: RequestBody,
        @Part("token") token: RequestBody,
        @Part file: MultipartBody.Part
    ):
            Call<ArticleResponse>

    @POST("articles/load")
    fun loadArticleById(
        @Query("articleGUID") articleGUID: String,
        @Query("userGUID") userGUID: String,
        @Query("token") token: String
    ):
            Call<ArticleResponse>

    @POST("articles/getallarticles")
    fun loadArticles(
        @Query("sectionGUID") sectionGUID: String,
        @Query("userGUID") userGUID: String,
        @Query("token") token: String
    ):
            Call<MultipleArticleResponse>

    @POST("article/load")
    fun loadArticleById(
        @Query("articleGUID") articleGUID: List<String>,
        @Query("userGUID") userGUID: String,
        @Query("token") token: String
    ):
            Call<MultipleArticleResponse>

    @POST("article/map")
    fun loadArticleMap(
        @Query("sectionGUID") sectionGUID: String,
        @Query("count") count: Long,
        @Query("offset") offset: Long,
        @Query("userGUID") userGUID: String,
        @Query("token") token: String
    ):
            Call<ArticleMapResponse>

    @POST("article/map")
    fun loadArticleMap(
        @Query("sectionGUID") sectionGUID: String,
        @Query("userGUID") userGUID: String,
        @Query("token") token: String
    ):
            Call<ArticleMapResponse>

    @GET("articles/getallcomments")
    fun loadComment(
        @Query("articleGUID") articleGUID: String,
        @Query("userGUID") userGUID: String,
        @Query("token") token: String
    ):
            Call<CommentResponse>

    @POST("articles/addcomment")
    fun leaveComment(
        @Query("articleGUID") articleGUID: String,
        @Query("message") message: String,
        @Query("userGUID") userGUID: String,
        @Query("token") token: String
    ):
            Call<CommentResponse>

    @POST("favourite/load")
    fun loadFavourite(
        @Query("userGUID") userGUID: String,
        @Query("token") token: String
    ):
            Call<FavouriteResponse>

    @POST("favourite/set")
    fun addFavourite(
        @Query("articleGUID") articleGUID: String,
        @Query("userGUID") userGUID: String,
        @Query("token") token: String,
        @Query("status") status: Boolean
    ):
            Call<FavouriteResponse>

    @POST("favourite/like")
    fun addLike(
        @Query("articleGUID") articleGUID: String,
        @Query("userGUID") userGUID: String,
        @Query("token") token: String,
        @Query("status") status: Boolean
    ):
            Call<LikeResponse>

    @POST("articles/sections")
    fun loadSections(
        @Query("userGUID") userGUID: String,
        @Query("token") token: String
    ):
            Call<SectionResponse>

}