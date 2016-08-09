package com.example.loveuApp.model;

import com.example.loveuApp.bean.userModel;

/**
 * Created by caolu on 2016/8/9.
 */
public class OtherUserModel {
    private String mag;
    private String state;
    private userModel data;

    public String getMag() {
        return mag;
    }

    public void setMag(String mag) {
        this.mag = mag;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public userModel getData() {
        return data;
    }

    public void setData(userModel data) {
        this.data = data;
    }
}
