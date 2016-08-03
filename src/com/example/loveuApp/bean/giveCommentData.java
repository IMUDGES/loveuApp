package com.example.loveuApp.bean;

import java.util.List;

/**
 * Created by yangy on 2016/8/3.
 */
public class giveCommentData {
    private String msg;
    private Integer state;
    private Integer num;
    private List<giveCommentModel> giveCommentModels;

    public giveCommentData() {
    }

    public giveCommentData(String msg, Integer state, Integer num, List<giveCommentModel> giveCommentModels) {
        this.msg = msg;
        this.state = state;
        this.num = num;
        this.giveCommentModels = giveCommentModels;
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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public List<giveCommentModel> getGiveCommentModels() {
        return giveCommentModels;
    }

    public void setGiveCommentModels(List<giveCommentModel> giveCommentModels) {
        this.giveCommentModels = giveCommentModels;
    }
}
