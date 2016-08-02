package com.example.loveuApp.bean;

/**
 * Created by dy on 2016/7/26.
 */
public class paiModel {
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
    private String UserPhoto;
    private String NickName;
    private Integer state;
    private Integer num;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getstate() {
        return state;
    }

    public void setstate(Integer state) {
        this.state = state;
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

    public String getNikeName() {
        return NickName;
    }

    public void setNikeName(String nikeName) {
        NickName = nikeName;
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

    public String getUserPhoto() {
        return UserPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        UserPhoto = userPhoto;
    }
}
