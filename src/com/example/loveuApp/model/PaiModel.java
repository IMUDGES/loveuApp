package com.example.loveuApp.model;

import com.example.loveuApp.bean.paiModel;

import java.util.List;

/**
 * Created by dy on 2016/8/4.
 */
public class PaiModel {

    private Integer state;
    private Integer num;
    private String msg;
    private List<paiModel> paidata;

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

    public List<paiModel> getPaidata() {
        return paidata;
    }

    public void setPaidata(List<paiModel> paidata) {
        this.paidata = paidata;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
