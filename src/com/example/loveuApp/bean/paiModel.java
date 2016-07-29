package com.example.loveuApp.bean;

/**
 * Created by dy on 2016/7/26.
 */
public class PaiModel {
    private Integer PaiId;
    private Integer UserId;
    private Integer PaiMoney;
    private String UpTime;
    private String DownTime;
    private String PaiInformation;
    private String PaiImage;
    private String PaiTitle;
    private Integer GetUser;
    private Integer State;
    private String msg;

    public PaiModel(){

    }

    public PaiModel(String downTime, Integer getUser, String msg, Integer paiId, String paiImage,
                    String paiInformation, Integer paiMoney, String paiTitle, Integer state, String upTime, Integer userId) {
        DownTime = downTime;
        GetUser = getUser;
        this.msg = msg;
        PaiId = paiId;
        PaiImage = paiImage;
        PaiInformation = paiInformation;
        PaiMoney = paiMoney;
        PaiTitle = paiTitle;
        State = state;
        UpTime = upTime;
        UserId = userId;
    }

    public String getDownTime() {
        return DownTime;
    }

    public void setDownTime(String downTime) {
        DownTime = downTime;
    }

    public Integer getGetUser() {
        return GetUser;
    }

    public void setGetUser(Integer getUser) {
        GetUser = getUser;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getPaiId() {
        return PaiId;
    }

    public void setPaiId(Integer paiId) {
        PaiId = paiId;
    }

    public String getPaiImage() {
        return PaiImage;
    }

    public void setPaiImage(String paiImage) {
        PaiImage = paiImage;
    }

    public String getPaiInformation() {
        return PaiInformation;
    }

    public void setPaiInformation(String paiInformation) {
        PaiInformation = paiInformation;
    }

    public Integer getPaiMoney() {
        return PaiMoney;
    }

    public void setPaiMoney(Integer paiMoney) {
        PaiMoney = paiMoney;
    }

    public String getPaiTitle() {
        return PaiTitle;
    }

    public void setPaiTitle(String paiTitle) {
        PaiTitle = paiTitle;
    }

    public Integer getState() {
        return State;
    }

    public void setState(Integer state) {
        State = state;
    }

    public String getUpTime() {
        return UpTime;
    }

    public void setUpTime(String upTime) {
        UpTime = upTime;
    }

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }
}
