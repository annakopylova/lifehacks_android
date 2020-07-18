package com.daily.lifehacks.app

import java.text.SimpleDateFormat
import java.util.*

object TimeHelper {

    fun calculateTimeString(date: Long): String{
        return date.toString()
    }

    fun getDate(timeStamp: Long): String {

        try {
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val netDate = Date(timeStamp)
            return sdf.format(netDate)
        } catch (ex: Exception) {
            return "xx"
        }
    }

    fun getDateWithTime(timeStamp: Long): String {

        try {
            val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")
            val netDate = Date(timeStamp)
            return sdf.format(netDate)
        } catch (ex: Exception) {
            return "xx"
        }
    }
}
