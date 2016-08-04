package com.example.loveuApp.model;

import com.example.loveuApp.bean.foodModel;

import java.util.List;

/**
 * Created by dy on 2016/8/4.
 */
public class FoodModel {

    private String msg;
    private Integer state;
    private Integer num;
    private List<foodModel> fooddata;

    public List<foodModel> getFooddata() {
        return fooddata;
    }

    public void setFooddata(List<foodModel> fooddata) {
        this.fooddata = fooddata;
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
