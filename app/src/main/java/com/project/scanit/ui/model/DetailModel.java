package com.project.scanit.ui.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailModel implements Parcelable{
    private String mText;
    private String mFormat;
    private String mTime;


    public DetailModel() {
    }

    public DetailModel(String text, String format, String time) {
        mText = text;
        mFormat = format;
        mTime = time;
    }

    protected DetailModel(Parcel in) {
        mText = in.readString();
        mFormat = in.readString();
        mTime = in.readString();
    }

    public static final Creator<DetailModel> CREATOR = new Creator<DetailModel>() {
        @Override
        public DetailModel createFromParcel(Parcel in) {
            return new DetailModel(in);
        }

        @Override
        public DetailModel[] newArray(int size) {
            return new DetailModel[size];
        }
    };

    public String getText() {
        return mText;
    }

    public String getFormat() {
        return mFormat;
    }

    public String getTime() {
        return mTime;
    }

    public void setText(String text) {
        mText = text;
    }

    public void setFormat(String format) {
        mFormat = format;
    }

    public void setTime(long time) {
        mTime = generateTime(time);
    }

    public void setTextTime(String time) {
        mTime = time;
    }

    private String generateTime(long time) {
        Date date = new Date(time);
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
        String dateFormatted = formatter.format(date);
        return dateFormatted;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mText);
        parcel.writeString(mFormat);
        parcel.writeString(mTime);
    }
}
