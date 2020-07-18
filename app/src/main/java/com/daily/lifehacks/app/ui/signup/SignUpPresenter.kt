package com.daily.lifehacks.app.ui.signup

import androidx.core.util.PatternsCompat
import com.daily.lifehacks.R
import com.daily.lifehacks.app.util.validator.validateEmail
import com.daily.lifehacks.app.util.validator.validateLogin
import com.daily.lifehacks.app.util.validator.validatePassword

class SignUpPresenter(override val view: ISignUpView?) : ISignUpPresenter {

    private val model: ISignUpModel? = SignUpModel(this)

    override fun onSuccess() {
        view?.onSuccess()
    }

    override fun onFailed(error: String) {
        view?.onFailed(error)
    }

    override fun enter(login: String, email: String, password: String): Boolean {
        // 1
        val usernameTrimmed = login.trim() // Убираем пробелы
        val emailTrimmed = email.trim()
        val passTrimmed = password.trim()
        val passCheck = validatePassword(passTrimmed) //2
        val emailCheck = validateEmail(emailTrimmed) || viewState == SignUpViewState.SIGN_IN//2.1
        val loginCheck = validateLogin(usernameTrimmed) //2.2

        if (passCheck && emailCheck && loginCheck) {
            if (viewState == SignUpViewState.SIGN_UP) {
                model?.trySignUp(login, email, password) //3
            } else if (viewState == SignUpViewState.SIGN_IN) {
                model?.trySignIn(login, password) //3
            }
            return true //4
        } else {
            //5
            if (!passCheck) { //6
                view?.onFailed(R.string.password_validation_error)
            } else if (!emailCheck && viewState == SignUpViewState.SIGN_UP) { //7
                view?.onFailed(R.string.email_validation_error)
            } else if (!loginCheck) { //8
                view?.onFailed(R.string.login_validation_error)
            }
            return false //4
        }
    }

    override fun startHomePage() {
        view?.startHomePage()
    }

    private var viewState: SignUpViewState = SignUpViewState.SIGN_IN

    override fun accountButtonClicked() {
        if (viewState == SignUpViewState.SIGN_UP) {
            viewState = SignUpViewState.SIGN_IN
        } else if (viewState == SignUpViewState.SIGN_IN) {
            viewState = SignUpViewState.SIGN_UP
        }
        view?.changeState(viewState)
    }
}