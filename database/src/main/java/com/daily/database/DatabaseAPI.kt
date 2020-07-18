package com.daily.database

import android.content.Context
import com.daily.database.entity.*

class DatabaseAPI(context: Context) {

    private val mDaoSession =
        DaoMaster(
            DaoOpenHelper(context, "articles-db", null)
                .writableDb
        )
            .newSession()

    fun getArticles(): List<Article> { // получить все статьи
        return mDaoSession.articleDao
            .queryBuilder()
            .orderDesc(ArticleDao.Properties.Creation_date)
            .list()
    }

    fun getArticle(articleGUID: String): Article { // получить одну
        return mDaoSession.articleDao.queryBuilder()
            .where(ArticleDao.Properties.ArticleGUID.eq(articleGUID))
            .unique()
    }

    fun getArticleBySection(sectionGUID: String): List<Article> { // получить одну
        return mDaoSession.articleDao.queryBuilder()
            .where(ArticleDao.Properties.SectionGUID.eq(sectionGUID))
            .orderDesc(ArticleDao.Properties.Creation_date)
            .list()
    }

    fun getSavedArticles(): List<Article> { // получить одну
        return mDaoSession.articleDao.queryBuilder()
            .where(ArticleDao.Properties.Saved.eq(true))
            .orderDesc(ArticleDao.Properties.Creation_date)
            .list()
    }

    fun createArticle(article: Article) { // создать
        mDaoSession.articleDao.insertOrReplaceInTx(article)
    }

    fun createArticle(article: List<Article>) { // создать
        mDaoSession.articleDao.insertOrReplaceInTx(article)
    }

    fun deleteArticle(article: Article) {
        mDaoSession.articleDao.delete(article)
    }

    fun getCommentsByArticle(articleGUID: String): List<Comment> {
        return mDaoSession.commentDao.queryBuilder()
            .where(CommentDao.Properties.ArticleGUID.eq(articleGUID))
            .list()
    }

    fun createComment(comment: Comment) {
        mDaoSession.commentDao.insertOrReplaceInTx(comment)
    }

    fun createComment(comment: List<Comment>) {
        mDaoSession.commentDao.insertOrReplaceInTx(comment)
    }

    fun deleteComment(comment: Comment) {
        mDaoSession.commentDao.delete(comment)
    }

    fun getSections(): List<Section> {
        return mDaoSession.sectionDao.loadAll()
    }

    fun getSectionsById(sectionGUID: String): Section {
        return mDaoSession.sectionDao.queryBuilder()
            .where(SectionDao.Properties.SectionGUID.eq(sectionGUID))
            .unique()
    }

    fun addSection(section: Section) {
        mDaoSession.sectionDao.insertOrReplaceInTx(section)
    }

    fun addSection(section: List<Section>) {
        mDaoSession.sectionDao.insertOrReplaceInTx(section)
    }

    fun deleteSection(section: Section) {
        mDaoSession.sectionDao.delete(section)
    }

    fun dropAll(){
        mDaoSession.sectionDao.deleteAll()
        mDaoSession.commentDao.deleteAll()
        mDaoSession.articleDao.deleteAll()
    }

    fun dropArticles(){
        mDaoSession.articleDao.deleteAll()
    }

    fun dropSections(){
        mDaoSession.sectionDao.deleteAll()
    }
}