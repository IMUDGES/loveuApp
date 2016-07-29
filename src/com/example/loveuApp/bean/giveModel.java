package com.example.loveuApp.bean;

/**
 * Created by dy on 2016/7/26.
 */
public class GiveModel {

    private Integer GiveId;
    private Integer UserId;
    private String GiveInformation;
    private String GiveImage;
    private Integer GetUser;
    private Integer state;
    private String msg;

    public GiveModel(){

    }

    public GiveModel(Integer getUser, Integer giveId, String giveImage, String giveInformation,
                     String msg, Integer state, Integer userId) {
        GetUser = getUser;
        GiveId = giveId;
        GiveImage = giveImage;
        GiveInformation = giveInformation;
        this.msg = msg;
        this.state = state;
        UserId = userId;
    }

    public Integer getGetUser() {
        return GetUser;
    }

    public void setGetUser(Integer getUser) {
        GetUser = getUser;
    }

    public Integer getGiveId() {
        return GiveId;
    }

    public void setGiveId(Integer giveId) {
        GiveId = giveId;
    }

    public String getGiveImage() {
        return GiveImage;
    }

    public void setGiveImage(String giveImage) {
        GiveImage = giveImage;
    }

    public String getGiveInformation() {
        return GiveInformation;
    }

    public void setGiveInformation(String giveInformation) {
        GiveInformation = giveInformation;
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
