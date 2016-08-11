package com.example.loveuApp.model;

import com.example.loveuApp.bean.userModel;

import java.util.List;

/**
 * Created by dy on 2016/8/11.
 */
public class FriendModel {
    private String msg;
    private Integer state;
    private List<userModel> data;

    public List<userModel> getData() {
        return data;
    }

    public void setData(List<userModel> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
