package com.example.loveuApp.left.model;

import com.example.loveuApp.bean.foodModel;

import java.util.List;

/**
 * Created by dy on 2016/9/20.
 */
public class FoodModelAll {

    private Integer num;
    private List<foodModel> foodlist;

    public List<foodModel> getFoodlist() {
        return foodlist;
    }

    public void setFoodlist(List<foodModel> foodlist) {
        this.foodlist = foodlist;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
