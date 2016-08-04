package com.example.loveuApp.model;

import com.example.loveuApp.bean.paiCommentModel;

import java.util.List;

/**
 * Created by dy on 2016/8/4.
 */
public class PaiCommentModel {
    private String msg;
    private Integer state;
    private Integer num;
    private List<paiCommentModel> paicommentdata;

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

    public List<paiCommentModel> getPaicommentdata() {
        return paicommentdata;
    }

    public void setPaicommentdata(List<paiCommentModel> paicommentdata) {
        this.paicommentdata = paicommentdata;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
