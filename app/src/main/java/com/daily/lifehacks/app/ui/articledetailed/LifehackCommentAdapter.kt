package com.daily.lifehacks.app.ui.articledetailed

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daily.database.entity.Comment
import com.daily.lifehacks.R
import java.util.*
import kotlin.collections.ArrayList

class LifehackCommentAdapter(
    val context: Context
) : RecyclerView.Adapter<CommentViewHolder>() {

    private val comments = ArrayList<Comment>()

    fun setList(list: List<Comment>) {
        comments.clear()
        Collections.reverse(list)
        comments.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.viewholder_comment,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = comments.size

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(comments[position])
    }

    fun addItem(comment: Comment) {
        comments.add(0, comment)
    }
}