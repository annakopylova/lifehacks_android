package com.daily.lifehacks.app.di.application

import com.daily.lifehacks.app.LifehackApplication
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [AppModule::class]
)
interface AppComponent {
    fun inject(application: LifehackApplication)
}