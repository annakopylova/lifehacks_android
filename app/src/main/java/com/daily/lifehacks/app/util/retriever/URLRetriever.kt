package com.daily.lifehacks.app.util.retriever

import com.daily.network.baseUrl

object URLRetriever {
    fun retrieveCorrectURL(url: String): String {
        return if(url.contains("$%")){
            "${baseUrl}/media/${url.substring(2, url.length)}"
        } else {
            url
        }
    }
}

