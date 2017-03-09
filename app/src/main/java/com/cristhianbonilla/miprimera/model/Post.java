package com.cristhianbonilla.miprimera.model;

import android.text.format.DateUtils;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by ASUS on 2/03/2017.
 */

public class Post {

    public  String uid;
    public  String author;
    public  String imgeUrl;
    public double timesMapCreated;

    public Post() {


    }

    public Post(String author, String imgeUrl, double timesMapCreated) {
        this.author = author;
        this.imgeUrl = imgeUrl;
        this.timesMapCreated = timesMapCreated;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImgeUrl() {
        return imgeUrl;
    }

    public void setImgeUrl(String imgeUrl) {
        this.imgeUrl = imgeUrl;
    }

    public double getTimesMapCreated() {
        return timesMapCreated;
    }

    public void setTimesMapCreated(double timesMapCreated) {
        this.timesMapCreated = timesMapCreated;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getRelativeTimeStamp(){

        return DateUtils.getRelativeTimeSpanString(
                (long)this.timesMapCreated,
                System.currentTimeMillis(),
                DateUtils.SECOND_IN_MILLIS,
                DateUtils.FORMAT_ABBREV_WEEKDAY).toString();

    }
}
