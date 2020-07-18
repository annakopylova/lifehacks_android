package com.daily.lifehacks.unit

import com.daily.lifehacks.app.ui.signup.SignUpPresenter
import junit.framework.Assert.assertEquals
import org.junit.Test

class SignUpPresenterTest {

    val errorMessage = "validation is broken"

    val signUpPresenter = SignUpPresenter(null)

    val username1 = "test11"
    val email1 = "test@gmail.com"
    val password1 = "test"

    val username2 = ";ФЫАФЫАФЫААЫ"
    val email2 = "asdasd!.asdcas.xz"
    val password2 = "1"

    val username3 = "1"
    val email3 = "1"
    val password3 = "1"

    val username4 = "anuta228"
    val email4 = "anakopa7@gmail.com"
    val password4 = "sobakasobakasobaka"

    val username4space = " anuta228 "
    val email4space = " anakopa7@gmail.com "
    val password4space = " sobakasobakasobaka "

    val username5 = "snickers"
    val email5 = "mini@snickers.com"
    val password5 = "bounty"

    // для ветвей и ограничений:
    // Множество1 [L<=2]
    val password6 = "1"
    // Множество2 [2<L<20]
    val password7 = "anuta228"
    // Множество3 [L>=20]
    val password8 = "asjdkfvdsjkblfj;asjhgsdjl;kfjbdgkl;adsfjhagvsjdfkl;skdfdsjkl;jdfjkl"
}