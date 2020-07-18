package com.daily.lifehacks.app.ui.signup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import com.daily.lifehacks.R
import com.daily.lifehacks.app.ui.articlelist.LifehackListView
import com.daily.lifehacks.app.util.lclass.LActivity
import com.daily.network.controller.auth.SignUpResponse
import kotlinx.android.synthetic.main.activity_signin.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpView : LActivity(), ISignUpView {

    override fun changeState(state: SignUpViewState) {
        if (state == SignUpViewState.SIGN_IN) {
            edtEmail.visibility = View.GONE
            edtEmail.setText("")
            edtLogin.setHint(R.string.login_email_hint)
            txvEnter.setText(R.string.enter)
            txvNoAccount.setText(R.string.no_account)
        } else if (state == SignUpViewState.SIGN_UP) {
            edtEmail.visibility = View.VISIBLE
            edtLogin.setHint(R.string.login_hint)
            txvEnter.setText(R.string.sign_up)
            txvNoAccount.setText(R.string.has_account)
        }
    }

    override fun getContext(): Context? {
        return this
    }

    override fun onSuccess() {
        progressBar1.visibility = View.GONE
        Toast.makeText(this, "Регистрация успешна", Toast.LENGTH_LONG).show()
        startHomePage()
    }

    override fun onFailed(error: Int) {
        progressBar1.visibility = View.GONE
        Toast.makeText(this, getString(error), Toast.LENGTH_LONG).show()
    }

    override fun onFailed(error: String) {
        progressBar1.visibility = View.GONE
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    val presenter: ISignUpPresenter? = SignUpPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (sharedpref.getToken().isNotEmpty()) {
            startHomePage()
            finish()
            return
        }

        setContentView(R.layout.activity_signin)

        btnNoAccount.setOnClickListener {
            presenter?.accountButtonClicked()
        }

        btnEnter.setOnClickListener {
            if (presenter?.enter(
                    edtLogin.text.toString().trim(),
                    edtEmail.text.toString().trim(),
                    edtPassword.text.toString().trim()
                ) != false
            ) {
                progressBar1.visibility = View.VISIBLE
                presenter?.accountButtonClicked()
            }
        }
    }

    override fun startHomePage() {
        startActivity(Intent(this, LifehackListView::class.java))
        finish()
    }
}