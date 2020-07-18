package com.daily.lifehacks.app.di.activity

import com.daily.lifehacks.app.di.application.AppComponent
import com.daily.lifehacks.app.util.lclass.LActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {
    fun inject(activity: LActivity)
}