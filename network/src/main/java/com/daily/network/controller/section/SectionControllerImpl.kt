package com.daily.network.controller.section

import com.daily.network.Api
import retrofit2.Call
import retrofit2.Callback

class SectionControllerImpl(val api: Api) : SectionController {
    override fun loadSections(token: String, userGUID: String, callback: Callback<SectionResponse>) {
        val call = api.loadSections(userGUID, token)
        call.enqueue(callback)
    }
}