package com.daily.lifehacks.app.di.network

import com.daily.network.NetworkGate
import dagger.Component

@NetworkScope
@Component(
    modules = [NetworkModule::class]
)
interface NetworkComponent {
    fun inject(networkGate: NetworkGate)
    fun getNetworkAPI(): NetworkGate
}