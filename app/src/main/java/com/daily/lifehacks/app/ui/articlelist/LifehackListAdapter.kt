package com.daily.lifehacks.app.ui.articlelist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daily.database.entity.Article
import com.daily.lifehacks.R
import com.daily.lifehacks.app.util.renderer.ArticleRenderer
import kotlinx.android.synthetic.main.viewholder_article.view.*

class LifehackListAdapter(
    private val context: Context,
    private val articles: ArrayList<Article>,
    val pickListener: LifehackPickListener
) : RecyclerView.Adapter<LifehackListAdapter.LifeHackViewHolder>() {


    private val cachedArticles = ArrayList<Article>(articles)

    var filter: String = ""

    @Synchronized
    fun search(query: String) {
        filter = query.toLowerCase()
        articles.clear()
        if(filter=="") {
            articles.addAll(cachedArticles)
        } else {
            for (article in cachedArticles) {
                if (article.title.toLowerCase().contains(filter) || article.description.toLowerCase().contains(filter)) {
                    articles.add(article)
                }
            }
        }
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LifeHackViewHolder {
        return LifeHackViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.viewholder_article,
                parent,
                false
            )
        )
    }

    fun updateElements(list: ArrayList<Article>) {
        notifyItemRangeRemoved(0, articles.size)
        cachedArticles.clear()
        cachedArticles.addAll(list)
        articles.clear()
        articles.addAll(cachedArticles)
        notifyItemRangeInserted(0, list.size)
        search(filter)
    }

    fun removeArticle(article: Article) {
        cachedArticles.remove(article)
        articles.remove(article)
    }

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: LifeHackViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    inner class LifeHackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(article: Article) {
            setupFields(itemView, article)
        }

        private fun setupFields(itemView: View, article: Article) {

            ArticleRenderer.renderArticle(
                article, pickListener.getArticleSection(article),
                itemView, context
            )

            itemView.clSaveZone.setOnClickListener {
                pickListener.onArticleSaveClicked(articles[adapterPosition], adapterPosition)
            }

            itemView.imgLikeButton.setOnClickListener {
                pickListener.onArticleLikeClicked(articles[adapterPosition], adapterPosition)
            }

            itemView.setOnClickListener {
                pickListener.onArticleClicked(articles[adapterPosition])
            }
        }
    }
}