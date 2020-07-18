package com.daily.lifehacks.app.ui.signup

import com.daily.lifehacks.app.util.lclass.LModel
import com.daily.network.controller.auth.SignInResponse
import com.daily.network.controller.auth.SignUpResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignUpModel(private val presenter: ISignUpPresenter?) : LModel(), ISignUpModel {

    override fun trySignUp(login: String, email: String, password: String) {
        network.signUp(login, email, hasher.getHash(password), object: Callback<SignUpResponse> {
            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<SignUpResponse>,
                response: Response<SignUpResponse>
            ) {
                if(response.body()?.success == true){
                    response.body()?.email?.let { sharedpref.setEmail(it) }
                    response.body()?.user_key?.let { sharedpref.setUserKey(it) }
                    response.body()?.token?.let { sharedpref.setToken(it) }
                    presenter?.onSuccess()
                } else {

                }
            }
        })
    }

    override fun trySignIn(login: String, password: String) {
        network.signIn(login, hasher.getHash(password), object: Callback<SignInResponse>{
            override fun onFailure(call: Call<SignInResponse>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<SignInResponse>,
                response: Response<SignInResponse>
            ) {
                if(response.body()?.success == true){
                    response.body()?.email?.let { sharedpref.setEmail(it) }
                    response.body()?.user_key?.let { sharedpref.setUserKey(it) }
                    response.body()?.token?.let { sharedpref.setToken(it) }
                    presenter?.onSuccess()
                } else {

                }
            }
        })
    }
}