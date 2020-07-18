package com.daily.lifehacks.app.util.validator

import androidx.core.util.PatternsCompat

fun validatePassword(password: String): Boolean {
    return password.length in 2..20
}

fun validateEmail(email: String): Boolean {
    return PatternsCompat.EMAIL_ADDRESS.matcher(email).matches() && email.isNotEmpty()
}

private val usernameRegex = "^[a-zA-z0-9.\\-_]{6,50}$".toRegex()

fun validateLogin(login: String): Boolean {
    return login.matches(usernameRegex)
}