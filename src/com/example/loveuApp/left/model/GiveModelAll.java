package com.example.loveuApp.left.model;

import com.example.loveuApp.bean.foodModel;
import com.example.loveuApp.bean.giveModel;

import java.util.List;

/**
 * Created by dy on 2016/9/20.
 */
public class GiveModelAll {

    private Integer num;
    private List<giveModel> givelist;

    public List<giveModel> getGivelist() {
        return givelist;
    }

    public void setGivelist(List<giveModel> givelist) {
        this.givelist = givelist;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
