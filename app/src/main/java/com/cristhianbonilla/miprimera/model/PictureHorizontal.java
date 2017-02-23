package com.cristhianbonilla.miprimera.model;

/**
 * Created by ASUS on 22/02/2017.
 */

public class PictureHorizontal {

    // 4 creamos nuestro datos

    private String picture;
    private String userName;
    private String time;
    private String  like_number = "0";

    public PictureHorizontal(String picture, String userName, String time, String like_number) {
        this.picture = picture;
        this.userName = userName;
        this.time = time;
        this.like_number = like_number;
    }


    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLike_number() {
        return like_number;
    }

    public void setLike_number(String like_number) {
        this.like_number = like_number;
    }
}
