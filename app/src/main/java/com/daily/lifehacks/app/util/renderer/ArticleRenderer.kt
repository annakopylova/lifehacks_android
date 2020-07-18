package com.daily.lifehacks.app.util.renderer

import android.content.Context
import android.view.View
import com.bumptech.glide.Glide
import com.daily.database.entity.Article
import com.daily.database.entity.Section
import com.daily.lifehacks.R
import com.daily.lifehacks.app.TimeHelper
import com.daily.lifehacks.app.util.retriever.URLRetriever
import com.daily.network.baseUrl
import kotlinx.android.synthetic.main.viewholder_article.view.*

object ArticleRenderer {
    fun renderArticle(article: Article, section: Section, itemView: View, context: Context) {

        itemView.txv_category.text = section.name
        itemView.txv_author.text = article.userName
        itemView.txv_title.text = article.title
        itemView.txvLikeCount.text = article.likeCount.toString()
        itemView.txv_date.text = article.creation_date.toString()

        Glide.with(context).load(URLRetriever.retrieveCorrectURL(article.picturePath))
            .into(itemView.img_picture)

        Glide.with(context).load(URLRetriever.retrieveCorrectURL(section.picturePath))
            .into(itemView.img_category)

        if (article.saved) {
            itemView.txv_save_text.text = context.getString(R.string.article_saved)
            itemView.img_save_button.setColorFilter(context.getColor(R.color.colorAccent))
        } else {
            itemView.txv_save_text.text = context.getString(R.string.article_not_saved)
            itemView.img_save_button.setColorFilter(context.getColor(R.color.textColor))
        }

        if (article.liked) {
            itemView.imgLikeButton.setColorFilter(context.getColor(R.color.colorAccent))
        } else {
            itemView.imgLikeButton.setColorFilter(context.getColor(R.color.textColor))
        }

        itemView.txvLikeCount.text = "${article.likeCount}"

        itemView.txv_date.text = TimeHelper.getDate(article.creation_date)
    }
}