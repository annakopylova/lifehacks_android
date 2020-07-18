package com.daily.lifehacks.app

import android.app.Application
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import com.daily.database.DatabaseAPI
import com.daily.lifehacks.app.di.application.AppComponent
import com.daily.lifehacks.app.di.application.AppModule
import com.daily.lifehacks.app.di.application.DaggerAppComponent
import com.daily.network.NetworkGate
import com.daily.sharedpreferences.SharedPreferencesAPI
import su.leff.hasher.Hasher
import javax.inject.Inject


/*
False positive "Unused symbol" for a custom Android application class referenced in AndroidManifest.xml file:
https://youtrack.jetbrains.net/issue/KT-27971
*/
class LifehackApplication : Application() {

    @Inject
    lateinit var database: DatabaseAPI

    @Inject
    lateinit var network: NetworkGate

    @Inject
    lateinit var sharedpref: SharedPreferencesAPI

    @Inject
    lateinit var hasher: Hasher

    override fun onCreate() {
        val builder = VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        application = this
        super.onCreate()

        component = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
        component.inject(this)

        database?.let { db ->
            if (db.getArticles().isEmpty()) {
                for (model in TemporaryArticleReceiver.getArticles()) {
//                    val article = Article(
//                        Random.Default.nextInt().toString(),
//                        "1273",
//                        model.title, model.description,
//                        model.picturePath, false, model.userName,
//                        model.saved,
//                        Random.Default.nextInt().toString(),
//                        System.currentTimeMillis(), model.likeCount
//                    )
//
//                    db.createArticle(article)
//                    db.addSection(Section("124", "Природа", "asdasd"))
//                    db.addSection(Section("1273", "Кулинария", "asdasd"))
//                    db.addSection(Section("12361", "Спорт", "asdasd"))
//                    db.addSection(Section("12723", "Музыка", "asdasd"))
//                    db.addSection(Section("12713", "Растения", "asdasd"))
//                    db.addSection(Section("12631", "Дача", "asdasd"))
//                    db.addSection(Section("12873", "Домашние дела", "asdasd"))
                }

            }
        }
    }

    companion object {
        lateinit var component: AppComponent
        lateinit var application: LifehackApplication
    }

}