package com.example.loveuApp.model;

import com.example.loveuApp.bean.xueModel;

import java.util.List;

/**
 * Created by caolu on 2016/8/4.
 */
public class XueModel {
    private String msg;
    private Integer state;
    private Integer num;
    private List<xueModel> xuedata;

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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public List<xueModel> getXuedata() {
        return xuedata;
    }

    public void setXuedata(List<xueModel> xuedata) {
        this.xuedata = xuedata;
    }
}
