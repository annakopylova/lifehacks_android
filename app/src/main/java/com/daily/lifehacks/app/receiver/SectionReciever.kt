package com.daily.lifehacks.app.receiver

import com.daily.database.entity.Section

interface SectionReciever {
    fun onSectionsReceived(sections: List<Section>)
}