package com.daily.lifehacks

import androidx.test.annotation.UiThreadTest
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.daily.database.entity.Article
import com.daily.lifehacks.app.ui.articlelist.LifehackListView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import junit.framework.Assert.assertEquals
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class Integration {

    // Устанавливаем экран, который будем тестить
    // @get:Rule нужен, так как Kotlin тест
    @get:Rule var rule: ActivityTestRule<LifehackListView> = ActivityTestRule(LifehackListView::class.java)

    @Test // Тест
    @UiThreadTest // Выполняем тест на главном потоке, так как только поток, который создал элемент, может производить с ним взаимодействия
    fun integration1(){
        val activity = rule.activity // Достаем экран
        val view = activity.findViewById<FloatingActionButton>(R.id.floatingButton) // Находим кнопку
        assertThat(view, notNullValue()) // Он не должен быть null
        assertThat(view, instanceOf(FloatingActionButton::class.java)) // Он должен быть именно типа FloatingActionButton
        assertEquals(view.tooltipText, "Создать статью")
        view.performClick() // Выполняем клик
    }

    @Test // Тест
    @UiThreadTest // Выполняем тест на главном потоке, так как только поток, который создал элемент, может производить с ним взаимодействия
    fun integration2(){
        val activity = rule.activity // Достаем экран
        assertThat(activity.getArticles(), notNullValue()) // Проверяем, не null ли список
        val article = activity.getArticles()!![0] // Получаем лайфхак
        assertThat(article, notNullValue()) // Проверяем, не null ли он
        assertThat(article.date, notNullValue()) // Проверяем, не null ли его дата
        assertThat(article, instanceOf(Article::class.java)) // Он должен быть именно типа Article
    }
}