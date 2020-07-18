package com.daily.network.controller.auth

data class SignInResponse(
    val success: Boolean,
    val user_key: String,
    val email: String,
    val token: String
)