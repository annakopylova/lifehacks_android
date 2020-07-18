package com.daily.network.controller.auth

data class SignUpResponse(
    val success: Boolean,
    val user_key: String,
    val email: String,
    val token: String
)