package com.example.loveuApp.bean;

/**
 * Created by dy on 2016/7/26.
 */
public class foodModel {

    private Integer FoodId;
    private Integer UserId;
    private String FoodArea;
    private String FoodInformation;
    private String GetUser;
    private String FoodTime;
    private String FoodWay;
    private Integer state;
    private String msg;

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
        this.state = state;
        UserId = userId;
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
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }
}
