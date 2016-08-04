package com.example.loveuApp.model;

import com.example.loveuApp.bean.runModel;

import java.util.List;

/**
 * Created by caolu on 2016/8/4.
 */
public class RunModel {
    private String msg;
    private Integer state;
    private Integer num;
    private List<runModel> rundata;

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

    public List<runModel> getRundata() {
        return rundata;
    }

    public void setRundata(List<runModel> rundata) {
        this.rundata = rundata;
    }
}
