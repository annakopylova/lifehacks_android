package com.daily.sharedpreferences

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.preference.PreferenceManager


class SharedPreferencesAPI(val context: Context) {

    private val preferences = context.getSharedPreferences("lifehack", Context.MODE_PRIVATE)

    fun setEmail(email: String) {
        preferences.edit().putString("email", email).commit()
    }

    fun getEmail(): String {
        return preferences.getString("email", "") ?: ""
    }

    fun setToken(token: String) {
        preferences.edit().putString("token", token).commit()
    }

    fun getToken(): String {
        return preferences.getString("token", "") ?: ""
    }

    fun setUserKey(userKey: String) {
        preferences.edit().putString("userkey", userKey).commit()
    }

    fun getUserKey(): String {
        return preferences.getString("userkey", "") ?: ""
    }

    fun clear() {
        preferences.edit().clear().commit()
    }
}