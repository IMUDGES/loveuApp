package com.example.loveuApp.left.model;

import com.example.loveuApp.bean.foodModel;
import com.example.loveuApp.bean.paiModel;

import java.util.List;

/**
 * Created by dy on 2016/9/20.
 */
public class PaiModelAll {

    private Integer num;
    private List<paiModel> pailist;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public List<paiModel> getPailist() {
        return pailist;
    }

    public void setPailist(List<paiModel> pailist) {
        this.pailist = pailist;
    }
}
