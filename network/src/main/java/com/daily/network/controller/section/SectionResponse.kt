package com.daily.network.controller.section

data class SectionResponse(
    val success: Boolean,
//    val sections: String
    val data: List<SectionInResponse>
)

data class SectionInResponse(val category_key: String, val text: String, val image_path: String)