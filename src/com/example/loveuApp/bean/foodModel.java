package com.example.loveuApp.bean;

import android.content.Intent;

import java.io.Serializable;

/**
 * Created by dy on 2016/7/26.
 */
public class foodModel implements Serializable {

    private Integer FoodId;
    private Integer UserId;
    private String FoodArea;
    private String FoodInformation;
    private String GetUser;
    private String FoodTime;
    private String FoodWay;
    private Integer State;
    private String msg;
    private Integer state;

    public foodModel(){

    }

    public foodModel(String foodArea, Integer foodId, String foodInformation, String foodTime, String foodWay,
                     String getUser, String msg, Integer state, Integer userId) {
        FoodArea = foodArea;
        FoodId = foodId;
        FoodInformation = foodInformation;
        FoodTime = foodTime;
        FoodWay = foodWay;
        GetUser = getUser;
        this.msg = msg;
        this.State = state;
        UserId = userId;

    }

    public Integer getstate(){
        return state;
    }

    public void setstate(Integer state){
        this.state = state;
    }

    public String getFoodArea() {
        return FoodArea;
    }

    public void setFoodArea(String foodArea) {
        FoodArea = foodArea;
    }

    public Integer getFoodId() {
        return FoodId;
    }

    public void setFoodId(Integer foodId) {
        FoodId = foodId;
    }

    public String getFoodInformation() {
        return FoodInformation;
    }

    public void setFoodInformation(String foodInformation) {
        FoodInformation = foodInformation;
    }

    public String getFoodTime() {
        return FoodTime;
    }

    public void setFoodTime(String foodTime) {
        FoodTime = foodTime;
    }

    public String getFoodWay() {
        return FoodWay;
    }

    public void setFoodWay(String foodWay) {
        FoodWay = foodWay;
    }

    public String getGetUser() {
        return GetUser;
    }

    public void setGetUser(String getUser) {
        GetUser = getUser;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getState() {
        return State;
    }

    public void setState(Integer state) {
        this.State = state;
    }

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }
}
