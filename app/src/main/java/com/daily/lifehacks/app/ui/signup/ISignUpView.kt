package com.daily.lifehacks.app.ui.signup

import android.content.Context

interface ISignUpView {
    fun onSuccess()
    fun onFailed(error: Int)
    fun onFailed(error: String)
    fun getContext(): Context?
    fun startHomePage()

    fun changeState(state: SignUpViewState)
}