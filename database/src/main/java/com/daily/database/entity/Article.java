package com.daily.database.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Article {

    @Id
    String articleGUID;

    @Property
    String sectionGUID;

    @Property
    String title; // название

    @Property
    String description; // описание

    @Property
    String picturePath; // картинка

    @Property
    boolean liked;

    @Property
    String userName;

    @Property
    boolean saved;

    @Property
    Long creation_date;

    @Property
    Long likeCount;




    @Generated(hash = 605432267)
    public Article(String articleGUID, String sectionGUID, String title,
            String description, String picturePath, boolean liked, String userName,
            boolean saved, Long creation_date, Long likeCount) {
        this.articleGUID = articleGUID;
        this.sectionGUID = sectionGUID;
        this.title = title;
        this.description = description;
        this.picturePath = picturePath;
        this.liked = liked;
        this.userName = userName;
        this.saved = saved;
        this.creation_date = creation_date;
        this.likeCount = likeCount;
    }

    @Generated(hash = 742516792)
    public Article() {
    }




    public String getArticleGUID() {
        return this.articleGUID;
    }

    public void setArticleGUID(String articleGUID) {
        this.articleGUID = articleGUID;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicturePath() {
        return this.picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public boolean getLiked() {
        return this.liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean getSaved() {
        return this.saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public Long getCreation_date() {
        return this.creation_date;
    }

    public void setCreation_date(Long creation_date) {
        this.creation_date = creation_date;
    }

    public Long getLikeCount() {
        return this.likeCount;
    }

    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    public String getSectionGUID() {
        return this.sectionGUID;
    }

    public void setSectionGUID(String sectionGUID) {
        this.sectionGUID = sectionGUID;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleGUID='" + articleGUID + '\'' +
                ", sectionGUID='" + sectionGUID + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", picturePath='" + picturePath + '\'' +
                ", liked=" + liked +
                ", userName='" + userName + '\'' +
                ", saved=" + saved +
                ", creation_date=" + creation_date +
                ", likeCount=" + likeCount +
                '}';
    }
}