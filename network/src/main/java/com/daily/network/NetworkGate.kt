package com.daily.network

import com.daily.network.controller.article.ArticleController
import com.daily.network.controller.article.ArticleResponse
import com.daily.network.controller.article.MultipleArticleResponse
import com.daily.network.controller.articlemap.ArticleMapController
import com.daily.network.controller.articlemap.ArticleMapResponse
import com.daily.network.controller.auth.AuthController
import com.daily.network.controller.auth.SignInResponse
import com.daily.network.controller.auth.SignUpResponse
import com.daily.network.controller.comment.CommentController
import com.daily.network.controller.comment.CommentResponse
import com.daily.network.controller.favourite.FavouriteController
import com.daily.network.controller.favourite.FavouriteResponse
import com.daily.network.controller.like.LikeController
import com.daily.network.controller.like.LikeResponse
import com.daily.network.controller.section.SectionController
import com.daily.network.controller.section.SectionResponse
import retrofit2.Callback
import java.io.File
import javax.inject.Inject

class NetworkGate @Inject constructor(
    private var articleController: ArticleController,
    private var articleMapController: ArticleMapController,
    private var authController: AuthController,
    private var commentController: CommentController,
    private var favouriteController: FavouriteController,
    private var likeController: LikeController,
    private var sectionController: SectionController
) : ArticleController,
    ArticleMapController,
    AuthController,
    CommentController,
    FavouriteController,
    LikeController,
    SectionController {
    override fun loadArticleById(
        GUID: String,
        token: String,
        userGUID: String,
        callback: Callback<ArticleResponse>
    ) {
        articleController.loadArticleById(GUID, token, userGUID, callback)
    }

    override fun loadArticleById(
        GUID: List<String>,
        token: String,
        userGUID: String,
        callback: Callback<MultipleArticleResponse>
    ) {
        articleController.loadArticleById(GUID, token, userGUID, callback)
    }

    override fun loadArticles(
        sectionGUID: String,
        token: String,
        userGUID: String,
        callback: Callback<MultipleArticleResponse>
    ) {
        articleController.loadArticles(sectionGUID, token, userGUID, callback)
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
        articleController.addArticle(
            title,
            description,
            category_key,
            userGUID,
            token,
            docfile,
            callback
        )
    }

    override fun loadArticleMap(
        count: Long,
        offset: Long,
        token: String,
        userGUID: String,
        callback: Callback<ArticleMapResponse>
    ) {
        articleMapController.loadArticleMap(count, offset, token, userGUID, callback)
    }

    override fun loadArticleMap(
        sectionGUID: String,
        count: Long,
        offset: Long,
        token: String,
        userGUID: String, callback: Callback<ArticleMapResponse>
    ) {
        articleMapController.loadArticleMap(sectionGUID, count, offset, token, userGUID, callback)
    }

    override fun signUp(
        login: String,
        email: String,
        password: String,
        callback: Callback<SignUpResponse>
    ) {
        authController.signUp(login, email, password, callback)
    }

    override fun signIn(input: String, password: String, callback: Callback<SignInResponse>) {
        authController.signIn(input, password, callback)
    }

    override fun loadComments(
        articleGUID: String,
        token: String,
        userGUID: String,
        callback: Callback<CommentResponse>
    ) {
        commentController.loadComments(articleGUID, token, userGUID, callback)
    }

    override fun leaveComment(
        articleGUID: String,
        message: String,
        token: String,
        userGUID: String, callback: Callback<CommentResponse>
    ) {
        commentController.leaveComment(articleGUID, message, token, userGUID, callback)
    }

    override fun getFavourite(
        userGUID: String,
        token: String,
        callback: Callback<FavouriteResponse>
    ) {
        favouriteController.getFavourite(userGUID, token, callback)
    }

    override fun addFavourite(
        articleGUID: String,
        userGUID: String,
        token: String, status: Boolean,
        callback: Callback<FavouriteResponse>
    ) {
        favouriteController.addFavourite(articleGUID, userGUID, token, status, callback)
    }

    override fun likeArticle(
        articleGUID: String,
        token: String,
        userGUID: String,
        status: Boolean,
        callback: Callback<LikeResponse>
    ) {
        likeController.likeArticle(articleGUID, token, userGUID, status, callback)
    }

    override fun loadSections(
        token: String,
        userGUID: String,
        callback: Callback<SectionResponse>
    ) {
        sectionController.loadSections(token, userGUID, callback)
    }
}