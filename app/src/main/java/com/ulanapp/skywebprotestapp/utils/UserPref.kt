package com.ulanapp.skywebprotestapp.utils

import com.chibatching.kotpref.KotprefModel

object UserPref: KotprefModel() {
    var loggedIn: Boolean by booleanPref(false)
    var login: String by stringPref("")
}