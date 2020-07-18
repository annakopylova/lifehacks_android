package com.daily.lifehacks.app.ui.settings

import android.content.Intent
import android.content.Intent.*
import android.os.Bundle
import com.daily.lifehacks.R
import com.daily.lifehacks.app.ui.signup.SignUpView
import com.daily.lifehacks.app.util.lclass.LActivity
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.activity_settings.imgBack
import kotlinx.android.synthetic.main.activity_settings.txvTitle
import kotlinx.android.synthetic.main.activity_watcher.*

class SettingsView : LActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        txvTitle.text = "Настройки"
        leave.text = "Выйти из аккаунта"
        leave.setOnClickListener {
            sharedpref.clear()
            database.dropAll()

            startActivity(Intent(this, SignUpView::class.java).apply {
                flags = FLAG_ACTIVITY_CLEAR_TOP
                flags = FLAG_ACTIVITY_NEW_TASK
                flags = FLAG_ACTIVITY_CLEAR_TASK
            })
        }
        imgBack.setOnClickListener {
            onBackPressed()
        }
    }
}