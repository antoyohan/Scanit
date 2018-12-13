package com.project.scanit.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "DETAIL_TABLE")
public class DetailEntity {


    @PrimaryKey
    @ColumnInfo(name = "timestamp")
    private long mId;
    @ColumnInfo(name = "title")
    private String mTitle;
    @ColumnInfo(name = "format")
    private String mFormat;
    @ColumnInfo(name = "time")
    private String mTime;

    public DetailEntity() {
    }

    public DetailEntity(long timeStamp) {
        mId = timeStamp;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getFormat() {
        return mFormat;
    }

    public void setFormat(String format) {
        mFormat = format;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }
}
