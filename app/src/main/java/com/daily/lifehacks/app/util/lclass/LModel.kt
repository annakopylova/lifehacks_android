package com.daily.lifehacks.app.util.lclass

import com.daily.database.DatabaseAPI
import com.daily.lifehacks.app.LifehackApplication
import com.daily.network.NetworkGate
import com.daily.sharedpreferences.SharedPreferencesAPI
import su.leff.hasher.Hasher

open class LModel {

    lateinit var app: LifehackApplication
    lateinit var sharedpref: SharedPreferencesAPI
    lateinit var database: DatabaseAPI
    lateinit var network: NetworkGate
    lateinit var hasher: Hasher

    init {
        app = LifehackApplication.application

        sharedpref = app.sharedpref
        database = app.database
        network = app.network
        hasher = app.hasher
    }
}