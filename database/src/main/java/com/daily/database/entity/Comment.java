package com.daily.database.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Comment {

    @Id
    String commentGUID;

    @Property
    String userName;

    @Property
    String body;

    @Property
    String articleGUID;

    @Property
    Long timestamp;

    @Generated(hash = 485106372)
    public Comment(String commentGUID, String userName, String body,
            String articleGUID, Long timestamp) {
        this.commentGUID = commentGUID;
        this.userName = userName;
        this.body = body;
        this.articleGUID = articleGUID;
        this.timestamp = timestamp;
    }

    @Generated(hash = 1669165771)
    public Comment() {
    }

    public String getCommentGUID() {
        return this.commentGUID;
    }

    public void setCommentGUID(String commentGUID) {
        this.commentGUID = commentGUID;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getArticleGUID() {
        return this.articleGUID;
    }

    public void setArticleGUID(String articleGUID) {
        this.articleGUID = articleGUID;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
