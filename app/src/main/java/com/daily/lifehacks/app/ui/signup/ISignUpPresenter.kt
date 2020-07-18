package com.daily.lifehacks.app.ui.signup

interface ISignUpPresenter  {
    val view: ISignUpView?
    fun enter(login: String, email: String, password: String): Boolean
    fun onSuccess()
    fun onFailed(error: String)
    fun startHomePage()

    fun accountButtonClicked()
}