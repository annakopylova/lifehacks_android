package com.daily.lifehacks.app.di.network

import com.daily.network.controller.article.ArticleController
import com.daily.network.controller.article.ArticleControllerImpl
import com.daily.network.controller.articlemap.ArticleMapController
import com.daily.network.controller.articlemap.ArticleMapControllerImpl
import com.daily.network.controller.auth.AuthController
import com.daily.network.controller.auth.AuthControllerImpl
import com.daily.network.controller.comment.CommentController
import com.daily.network.controller.comment.CommentControllerImpl
import com.daily.network.controller.favourite.FavouriteController
import com.daily.network.controller.favourite.FavouriteControllerImpl
import com.daily.network.controller.like.LikeController
import com.daily.network.controller.like.LikeControllerImpl
import com.daily.network.controller.section.SectionController
import com.daily.network.controller.section.SectionControllerImpl
import com.daily.network.tmdbApi
import dagger.Module
import dagger.Provides

@Module
class NetworkModule() {

    @Provides
    @NetworkScope
    fun article(): ArticleController = ArticleControllerImpl(tmdbApi)

    @Provides
    @NetworkScope
    fun articlemap(): ArticleMapController = ArticleMapControllerImpl(tmdbApi)

    @Provides
    @NetworkScope
    fun auth(): AuthController = AuthControllerImpl(tmdbApi)

    @Provides
    @NetworkScope
    fun comment(): CommentController = CommentControllerImpl(tmdbApi)

    @Provides
    @NetworkScope
    fun favourite(): FavouriteController = FavouriteControllerImpl(tmdbApi)

    @Provides
    @NetworkScope
    fun like(): LikeController = LikeControllerImpl(tmdbApi)

    @Provides
    @NetworkScope
    fun section(): SectionController = SectionControllerImpl(tmdbApi)
}