package com.daily.lifehacks.app.ui.signup

interface ISignUpModel {
    fun trySignUp(login: String, email: String, password: String)
    fun trySignIn(login: String, password: String)
}