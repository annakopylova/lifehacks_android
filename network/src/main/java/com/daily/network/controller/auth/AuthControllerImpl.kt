package com.daily.network.controller.auth

import com.daily.network.Api
import retrofit2.Call
import retrofit2.Callback

class AuthControllerImpl(val api: Api) : AuthController {
    override fun signUp(login: String, email: String, password: String, callback: Callback<SignUpResponse>) {
        val call = api.signUp(login, email, password)
        call.enqueue(callback)
    }

    override fun signIn(input: String, password: String, callback: Callback<SignInResponse>) {
        val call = api.signIn(input, password)
        call.enqueue(callback)
    }

}