package com.daily.lifehacks.unit

import com.daily.lifehacks.app.util.retriever.URLRetriever
import com.daily.network.baseUrl
import junit.framework.Assert.assertEquals
import org.junit.Test

class Retriever {

    private val link1 =
        "https://image.winudf.com/v2/image1/Y29tLmZha2VjYWxsMmdhbWVfc2NyZWVuXzBfMTU3MjQ1MDE4Nl8wNTI/screen-5.jpg?fakeurl=1&type=.jpg"
    private val link2 = "\$%documents/2020/06/13/Blank_Diagram_-_Page_2.png"
    private val expectedResultLink2 =
        "${baseUrl}/media/documents/2020/06/13/Blank_Diagram_-_Page_2.png"

    @Test
    fun test() {
        assertEquals("external", link1, URLRetriever.retrieveCorrectURL(link1))
        assertEquals("local", expectedResultLink2, URLRetriever.retrieveCorrectURL(link2))
    }
}