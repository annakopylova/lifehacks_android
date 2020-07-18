package com.daily.lifehacks.app.ui.articledetailed

import android.app.Activity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.daily.database.entity.Comment
import com.daily.lifehacks.R
import com.daily.lifehacks.app.util.guid
import com.daily.lifehacks.app.util.lclass.LActivity
import com.daily.lifehacks.app.util.renderer.ArticleRenderer
import com.daily.lifehacks.app.util.token
import com.daily.network.controller.comment.CommentResponse
import com.daily.network.controller.favourite.FavouriteResponse
import com.daily.network.controller.like.LikeResponse
import kotlinx.android.synthetic.main.activity_watcher.*
import kotlinx.android.synthetic.main.viewholder_article.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.random.Random

class LifehackWatcherView : LActivity() {

    lateinit var commentAdapter: LifehackCommentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_watcher)

        val article = database.getArticle(intent.getStringExtra("articleGUID"))
        val section = database.getSectionsById(article.sectionGUID)

        ArticleRenderer.renderArticle(article, section, clLifehack, this)

        txvTitle.text = "Лайфхак"
        imgBack.setOnClickListener {
            onBackPressed()
        }

        txvDescription.text = article.description

        rvComments.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        commentAdapter = LifehackCommentAdapter(this)
        rvComments.adapter = commentAdapter

        network.loadComments(article.articleGUID, token, guid, object : Callback<CommentResponse> {
            override fun onFailure(call: Call<CommentResponse>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<CommentResponse>,
                response: Response<CommentResponse>
            ) {
                val comments = response.body()?.data
                comments?.let {
                    val list = ArrayList<Comment>()
                    for (comment in comments) {
                        val commentBD = Comment(
                            comment.comment_key,
                            comment.name,
                            comment.text,
                            comment.article_key,
                            (comment.creation_date * 1000).toLong()
                        )
                        database.createComment(commentBD)
                        list.add(commentBD)
                    }
                    commentAdapter.setList(list)
                }
            }

        })


        imgSend.setOnClickListener {
            val text = edtMessage.text.toString()

            network.leaveComment(
                article.articleGUID,
                text,
                token,
                guid,
                object : Callback<CommentResponse> {
                    override fun onFailure(call: Call<CommentResponse>, t: Throwable) {

                    }

                    override fun onResponse(
                        call: Call<CommentResponse>,
                        response: Response<CommentResponse>
                    ) {
                        val comments = response.body()?.data
                        comments?.let {
                            val list = ArrayList<Comment>()
                            for (comment in comments) {
                                val commentBD = Comment(
                                    comment.comment_key,
                                    comment.name,
                                    comment.text,
                                    comment.article_key,
                                    (comment.creation_date * 1000).toLong()
                                )
                                database.createComment(commentBD)
                                list.add(commentBD)
                            }
                            commentAdapter.setList(list)
                        }
                    }
                })

            (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
                .hideSoftInputFromWindow(edtMessage.windowToken, 0)
            edtMessage.setText("")
            edtMessage.clearFocus()
        }

        clSaveZone.setOnClickListener {
            network.addFavourite(
                article.articleGUID,
                guid,
                token,
                !article.saved,
                object : Callback<FavouriteResponse> {
                    override fun onFailure(call: Call<FavouriteResponse>, t: Throwable) {

                    }

                    override fun onResponse(
                        call: Call<FavouriteResponse>,
                        response: Response<FavouriteResponse>
                    ) {
                        val data = response.body()?.data
                        data?.let {
                            article.saved = data.favourite
                            database.createArticle(article)
                            ArticleRenderer.renderArticle(
                                article,
                                section,
                                clLifehack,
                                this@LifehackWatcherView
                            )
                        }
                    }
                })
            ArticleRenderer.renderArticle(article, section, clLifehack, this)
        }

        imgLikeButton.setOnClickListener {

            network.likeArticle(
                article.articleGUID,
                token,
                guid,
                !article.liked,
                object : Callback<LikeResponse> {
                    override fun onFailure(call: Call<LikeResponse>, t: Throwable) {

                    }

                    override fun onResponse(
                        call: Call<LikeResponse>,
                        response: Response<LikeResponse>
                    ) {
                        val data = response.body()?.data
                        data?.let {
                            article.liked = data.liked
                            if (article.liked) {
                                article.likeCount += 1
                            } else {
                                article.likeCount -= 1
                            }
                            database.createArticle(article)
                            ArticleRenderer.renderArticle(
                                article,
                                section,
                                clLifehack,
                                this@LifehackWatcherView
                            )
                        }
                    }
                })
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}