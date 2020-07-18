package com.daily.lifehacks.app.util.lclass

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.daily.database.DatabaseAPI
import com.daily.lifehacks.app.LifehackApplication
import com.daily.network.NetworkGate
import com.daily.sharedpreferences.SharedPreferencesAPI
import com.facebook.stetho.inspector.protocol.module.Database
import su.leff.hasher.Hasher

open class LActivity : AppCompatActivity() {

    lateinit var app: LifehackApplication
    lateinit var sharedpref: SharedPreferencesAPI
    lateinit var database: DatabaseAPI
    lateinit var network: NetworkGate
    lateinit var hasher: Hasher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        app = (application as LifehackApplication)

        sharedpref = app.sharedpref
        database = app.database
        network = app.network
        hasher = app.hasher
    }


}