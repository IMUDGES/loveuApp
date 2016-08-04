package com.example.loveuApp.model;

import com.example.loveuApp.bean.helpModel;

import java.util.List;

/**
 * Created by dy on 2016/8/4.
 */
public class HelpModel {

    private String msg;
    private Integer state;
    private Integer num;
    private List<helpModel> helpdata;

    public List<helpModel> getHelpdata() {
        return helpdata;
    }

    public void setHelpdata(List<helpModel> helpdata) {
        this.helpdata = helpdata;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
