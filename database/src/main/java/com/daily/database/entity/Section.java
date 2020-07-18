package com.daily.database.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Section {

    @Id
    String sectionGUID;

    @Property
    String name;

    @Property
    String picturePath;

    @Generated(hash = 399759818)
    public Section(String sectionGUID, String name, String picturePath) {
        this.sectionGUID = sectionGUID;
        this.name = name;
        this.picturePath = picturePath;
    }

    @Generated(hash = 111791983)
    public Section() {
    }

    public String getSectionGUID() {
        return this.sectionGUID;
    }

    public void setSectionGUID(String sectionGUID) {
        this.sectionGUID = sectionGUID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicturePath() {
        return this.picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }
}
