package com.example.loveuApp.bean;

/**
 * Created by yangy on 2016/8/10.
 */
public class giveCommentReturnModel {
    private Integer state;
    private String msg;

    public giveCommentReturnModel() {
    }

    public giveCommentReturnModel(Integer state, String msg) {
        this.state = state;
        this.msg = msg;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
