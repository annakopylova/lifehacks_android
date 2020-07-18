package com.daily.lifehacks.app.ui.articlelist

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.annotation.VisibleForTesting
import androidx.recyclerview.widget.LinearLayoutManager
import com.daily.database.entity.Article
import com.daily.lifehacks.R
import kotlinx.android.synthetic.main.activity_main.*
import androidx.recyclerview.widget.SimpleItemAnimator
import br.com.mauker.materialsearchview.MaterialSearchView
import com.daily.database.entity.Section
import com.daily.lifehacks.app.ui.articledetailed.LifehackWatcherView
import com.daily.lifehacks.app.ui.createlifehack.CreateLifehackView
import com.daily.lifehacks.app.ui.settings.SettingsView
import com.daily.lifehacks.app.util.guid
import com.daily.lifehacks.app.util.lclass.LActivity
import com.daily.lifehacks.app.util.token
import com.daily.network.controller.article.ArticleJSON
import com.daily.network.controller.article.MultipleArticleResponse
import com.daily.network.controller.favourite.FavouriteResponse
import com.daily.network.controller.like.LikeResponse
import com.daily.network.controller.section.SectionInResponse
import com.daily.network.controller.section.SectionResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LifehackListView : LActivity(), SectionPickListener, LifehackPickListener {

    lateinit var rvSectionsAdapter: SectionListAdapter
    lateinit var rvArticleAdapter: LifehackListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        floatingButton.setOnClickListener {
            startActivity(Intent(this, CreateLifehackView::class.java))
        }
        setupAdapter()

        val handler = Handler(Looper.getMainLooper())

        updateDatabase()
//        handler.postDelayed({
//            updateDatabase()
//        }, 10000)

        imgSettings.setOnClickListener {
            startActivity(Intent(this, SettingsView::class.java))
        }

        imgSearch.setOnClickListener {
            search.openSearch()
        }
        search.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { search(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })

        txvTitle.setOnClickListener {
            cancelSearch()
        }
    }

    fun search(string: String) {
        search.closeSearch()
        txvTitle.text = string
        rvArticleAdapter.search(string)
    }

    fun cancelSearch() {
        rvArticleAdapter.search("")
        txvTitle.text = getString(R.string.app_name)
    }

    private fun setupAdapter() {
        rvSectionsAdapter = SectionListAdapter(database.getSections(), this, this)
        rvSections.adapter = rvSectionsAdapter
        rvSections.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


        rvArticles.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val articles = getArticles()
        articles?.let {
            rvArticleAdapter = LifehackListAdapter(this, it as ArrayList<Article>, this)
            rvArticles.adapter = rvArticleAdapter

        }
        (rvArticles.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
    }

    private fun updateAdapters() {
        runOnUiThread {
            val sections = database.getSections()
            rvSectionsAdapter.setList(sections as ArrayList<Section>)

            if (rvSectionsAdapter.chosenSection.sectionGUID == "-1") {
                rvArticleAdapter.updateElements(
                    database.getArticles() as ArrayList<Article>
                )
            } else if (rvSectionsAdapter.chosenSection.sectionGUID == "-2") {
                rvArticleAdapter.updateElements(
                    database.getSavedArticles() as ArrayList<Article>
                )
            } else {
                rvArticleAdapter.updateElements(
                    database.getArticleBySection(
                        rvSectionsAdapter.chosenSection.sectionGUID
                    ) as ArrayList<Article>
                )
            }
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun getArticles(): MutableList<Article>? {
        return database.getArticles() as ArrayList<Article>
    }

    private fun updateAdapter() {
        val articles = getArticles()
        articles?.let {
            (rvArticles.adapter as LifehackListAdapter).updateElements(it as ArrayList<Article>)
        }
    }

    override fun onResume() {
        super.onResume()
        updateAdapters()
    }

    override fun onSectionPicked(section: Section) {
        if (section.sectionGUID == "-1") {
            rvArticleAdapter.updateElements(
                database.getArticles() as ArrayList<Article>
            )
        } else if (section.sectionGUID == "-2") {
            rvArticleAdapter.updateElements(
                database.getSavedArticles() as ArrayList<Article>
            )
        } else {
            rvArticleAdapter.updateElements(
                database.getArticleBySection(
                    section.sectionGUID
                ) as ArrayList<Article>
            )
        }
    }

    override fun onArticleClicked(article: Article) {
        val intent = Intent(this, LifehackWatcherView::class.java)
        intent.putExtra("articleGUID", article.articleGUID)
        startActivity(intent)
    }

    override fun onArticleLikeClicked(article: Article, position: Int) {

        network.likeArticle(
            article.articleGUID,
            token,
            guid,
            !article.liked,
            object : Callback<LikeResponse> {
                override fun onFailure(call: Call<LikeResponse>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<LikeResponse>,
                    response: Response<LikeResponse>
                ) {
                    val data = response.body()?.data
                    data?.let {
                        article.liked = data.liked
                        if (article.liked) {
                            article.likeCount += 1
                        } else {
                            article.likeCount -= 1
                        }
                        database.createArticle(article)
                        rvArticleAdapter.notifyItemChanged(position)
                    }
                }
            })
    }

    override fun onArticleSaveClicked(article: Article, position: Int) {

        network.addFavourite(
            article.articleGUID,
            guid,
            token,
            !article.saved,
            object : Callback<FavouriteResponse> {
                override fun onFailure(call: Call<FavouriteResponse>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<FavouriteResponse>,
                    response: Response<FavouriteResponse>
                ) {
                    val data = response.body()?.data

                    data?.let {
                        article.saved = data.favourite
                        if (rvSectionsAdapter.chosenSectionIndex == 1) {
                            rvArticleAdapter.removeArticle(article)
                            rvArticleAdapter.notifyItemRemoved(position)
                        } else {
                            rvArticleAdapter.notifyItemChanged(position)
                        }
                        database.createArticle(article)
                    }
                }

            })
    }

    override fun onBackPressed() {
        if (search.isOpen()) {
            // Close the search on the back button press.
            search.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

    override fun getArticleSection(article: Article): Section {
        return database.getSectionsById(article.sectionGUID)
    }

    private fun updateDatabase() {
        network.loadSections(token, guid, object : Callback<SectionResponse> {
            override fun onFailure(call: Call<SectionResponse>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<SectionResponse>,
                response: Response<SectionResponse>
            ) {
                val sectionData = response.body()?.data
                if (sectionData != null) {
                    val sectionsInStrings = sectionData as ArrayList<SectionInResponse>

                    val sectionList = ArrayList<Section>()
                    for (section in sectionsInStrings) {
                        val dbSection =
                            Section(section.category_key, section.text, section.image_path)
                        sectionList.add(dbSection)
                    }

                    database.dropSections()
                    database.dropArticles()
                    database.addSection(sectionList)

                    for (section in database.getSections()) {
                        network.loadArticles(
                            section.sectionGUID,
                            token,
                            guid,
                            object : Callback<MultipleArticleResponse> {
                                override fun onFailure(
                                    call: Call<MultipleArticleResponse>,
                                    t: Throwable
                                ) {

                                }

                                override fun onResponse(
                                    call: Call<MultipleArticleResponse>,
                                    response: Response<MultipleArticleResponse>
                                ) {
                                    val body = response.body()?.data
                                    if (body != null) {
                                        val articlesList = ArrayList<Article>()
                                        val articles = body as ArrayList<ArticleJSON>
                                        for (artic in articles) {
                                            val article = Article(
                                                artic.article_key,
                                                artic.category_key,
                                                artic.title,
                                                artic.description,
                                                artic.docfile,
                                                artic.liked,
                                                artic.author,
                                                artic.favourite,
                                                (artic.creation_date * 1000).toLong(),
                                                artic.like_count
                                            )
                                            articlesList.add(article)
                                        }
                                        database.createArticle(articlesList)
                                        updateAdapters()
                                    }
                                }
                            })
                    }
                }
            }
        })
    }
}