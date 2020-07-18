package com.daily.lifehacks.app.ui.createlifehack

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.daily.database.entity.Article
import com.daily.database.entity.Section
import com.daily.lifehacks.R
import com.daily.lifehacks.app.util.ImageChooserUtility
import com.daily.lifehacks.app.util.guid
import com.daily.lifehacks.app.util.lclass.LActivity
import com.daily.lifehacks.app.util.token
import com.daily.network.controller.article.ArticleResponse
import com.jaredrummler.materialspinner.MaterialSpinner
import kotlinx.android.synthetic.main.activity_create.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

class CreateLifehackView : LActivity() {

    val CODE = 1

    lateinit var file: File

    lateinit var chosenSection: Section

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_create)

        txvTitle.text = "Создать лайфхак"

        imgBack.setOnClickListener {
            onBackPressed()
        }

        floatingButton.setOnClickListener {
            createLifehack(file)
        }

        btnPicture.setOnClickListener {
            val intent = ImageChooserUtility.getPickImageIntent(this)
            startActivityForResult(intent, CODE)
        }

        (floatingButton.context as Activity).currentFocus

        val sections = database.getSections()
        val nameList = ArrayList<String>()
        for (section in sections) {
            nameList.add(section.name)
        }
        spinnerSection.setItems(nameList)
        spinnerSection.setOnItemSelectedListener(object :
            MaterialSpinner.OnItemSelectedListener<String> {
            override fun onItemSelected(
                view: MaterialSpinner?,
                position: Int,
                id: Long,
                item: String?
            ) {
                chosenSection = sections[position]
            }

        })
        chosenSection = sections[0]
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == CODE) {
                val bitmap = ImageChooserUtility.getImageFromResult(this, resultCode, data)
                file = saveBitmapToFile(bitmap, requestCode)
            }
        }
    }

    private fun saveBitmapToFile(bitmap: Bitmap, position: Int): File {
        val filename = "${position}_${System.currentTimeMillis()}.jpeg"
        val file = File(cacheDir, filename)
        file.createNewFile()

        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 75, byteArrayOutputStream)
        val bitmapData = byteArrayOutputStream.toByteArray()

        val fos = FileOutputStream(file)
        fos.write(bitmapData)
        fos.flush()
        fos.close()

        imgPic.setImageBitmap(bitmap)

        return file
    }

    private fun createLifehack(file: File) {

        if (file == null) {
            Toast.makeText(
                this@CreateLifehackView,
                "Необходимо отправить изображение!",
                Toast.LENGTH_LONG
            ).show()
        } else {
            val title = edtTitle.text.toString()
            val description = edtDescription.text.toString()
            val category = chosenSection.sectionGUID

            if (title.isEmpty() || description.isEmpty() || category.isEmpty()) {
                Toast.makeText(
                    this@CreateLifehackView,
                    "Не все поля заполнены!",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                network.addArticle(title, description, category, guid, token, file, object :
                    Callback<ArticleResponse> {
                    override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {
                        println(t.toString())
                    }

                    override fun onResponse(
                        call: Call<ArticleResponse>,
                        response: Response<ArticleResponse>
                    ) {
                        Toast.makeText(
                            this@CreateLifehackView,
                            "Статья успешно отправлена!",
                            Toast.LENGTH_LONG
                        ).show()
                        finish()
                    }
                })
            }
        }
    }
}