package com.daily.lifehacks.app.di.application

import android.app.Application
import com.daily.database.DatabaseAPI
import com.daily.lifehacks.app.di.network.DaggerNetworkComponent
import com.daily.lifehacks.app.di.network.NetworkModule
import com.daily.network.NetworkGate
import com.daily.sharedpreferences.SharedPreferencesAPI
import dagger.Module
import dagger.Provides
import su.leff.hasher.Hasher

@Module
class AppModule(private val application: Application) {

    @Provides
    @AppScope
    internal fun application(): Application = application

    @Provides
    @AppScope
    internal fun database(): DatabaseAPI = DatabaseAPI(application)

    @Provides
    @AppScope
    internal fun network(): NetworkGate = DaggerNetworkComponent
        .builder().networkModule(NetworkModule()).build().getNetworkAPI()

    @Provides
    @AppScope
    internal fun sharedpref(): SharedPreferencesAPI =
        SharedPreferencesAPI(application.applicationContext)

    @Provides
    @AppScope
    internal fun hasher(): Hasher = Hasher()


}