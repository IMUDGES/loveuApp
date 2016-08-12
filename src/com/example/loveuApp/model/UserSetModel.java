package com.example.loveuApp.model;

import com.example.loveuApp.bean.userSetModel;

/**
 * Created by 1111 on 2016/8/11.
 */
public class UserSetModel {
    private String state;
    private String msg;
    private userSetModel data;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public userSetModel getData() {
        return data;
    }
}
