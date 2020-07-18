package com.daily.lifehacks.app.ui.articlelist

import com.daily.database.entity.Section

interface SectionPickListener {
    fun onSectionPicked(section: Section)
}