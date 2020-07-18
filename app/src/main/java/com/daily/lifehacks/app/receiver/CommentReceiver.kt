package com.daily.lifehacks.app.receiver

import com.daily.database.entity.Comment

interface CommentReceiver {
    fun onCommentsReceived(comments: List<Comment>)
}