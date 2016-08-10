package com.example.loveuApp.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yangy on 2016/8/3.
 */
public class giveData implements Serializable {
    private String msg;
    private Integer num;
    private Integer state;
    private List<giveModel> giveModels;

    public giveData() {
    }

    public giveData(String msg, Integer num, Integer state, List<giveModel> giveModels) {
        this.msg = msg;
        this.num = num;
        this.state = state;
        this.giveModels = giveModels;
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

    public List<giveModel> getGiveModels() {
        return giveModels;
    }

    public void setGiveModels(List<giveModel> giveModels) {
        this.giveModels = giveModels;
    }
}

