package com.daily.network.controller.section

import com.daily.network.Api
import retrofit2.Callback

interface SectionController {
    fun loadSections(token: String, userGUID: String, callback: Callback<SectionResponse>)
}