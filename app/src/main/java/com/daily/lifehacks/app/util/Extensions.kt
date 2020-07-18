package com.daily.lifehacks.app.util

import android.content.res.Resources
import com.daily.lifehacks.app.util.lclass.LActivity

fun pxToDp(px: Int): Int {
    return (px / Resources.getSystem().getDisplayMetrics().density).toInt()
}

fun dpToPx(dp: Int): Int {
    return (dp * Resources.getSystem().getDisplayMetrics().density).toInt()
}

val LActivity.guid : String
    get() = sharedpref.getUserKey()

val LActivity.token : String
    get() = sharedpref.getToken()