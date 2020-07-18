package com.daily.lifehacks.app.ui.articledetailed

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.daily.database.entity.Comment
import com.daily.lifehacks.app.TimeHelper
import kotlinx.android.synthetic.main.viewholder_comment.view.*

class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(comment: Comment) {
        itemView.txvUser.setText(comment.userName)
        itemView.txvMessage.setText(comment.body)
        itemView.txvDate.setText(TimeHelper.getDateWithTime(comment.timestamp))
    }
}