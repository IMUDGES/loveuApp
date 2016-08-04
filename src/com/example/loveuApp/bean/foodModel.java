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
    private String UserPhoto;
    private String NickName;
    private int UserSex;

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public int getUserSex() {
        return UserSex;
    }

    public void setUserSex(int UserSex) {
        this.UserSex = UserSex;
    }

    public String getUserPhoto() {
        return UserPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        UserPhoto = userPhoto;
    }

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
        this.State = state;
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
