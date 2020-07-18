package com.daily.network.controller.auth

import com.daily.network.Api
import retrofit2.Callback

interface AuthController  {
    fun signUp(login: String, email: String, password: String, callback: Callback<SignUpResponse>)
    fun signIn(input: String, password: String, callback: Callback<SignInResponse>)
}