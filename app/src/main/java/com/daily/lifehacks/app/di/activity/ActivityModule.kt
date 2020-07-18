package com.daily.lifehacks.app.di.activity

import com.daily.lifehacks.app.util.lclass.LActivity
import dagger.Module

@Module
class ActivityModule(private val activity: LActivity) {

//    @Provides
//    @ActivityScope
//    fun sharedPref(): SharedPref = SharedPrefImpl(activity)
}