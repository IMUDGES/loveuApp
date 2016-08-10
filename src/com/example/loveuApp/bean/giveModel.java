package com.example.loveuApp.bean;

import java.io.Serializable;

/**
 * Created by dy on 2016/7/26.
 */
public class giveModel implements Serializable {

    private Integer GiveId;
    private String GiveImage;
    private Integer UserId;
    private String GiveInformation;
    private String UserPhoto;
    private String UserSex;
    private String NickName;
    private Integer State;
    public giveModel(){

    }
    public giveModel(Integer giveId, String giveImage, Integer userId, String giveInformation, String userPhoto, String userSex, String nickName, Integer state) {
        GiveId = giveId;
        GiveImage = giveImage;
        UserId = userId;
        GiveInformation = giveInformation;
        UserPhoto = userPhoto;
        UserSex = userSex;
        NickName = nickName;
        State = state;
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

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }

    public String getGiveInformation() {
        return GiveInformation;
    }

    public void setGiveInformation(String giveInformation) {
        GiveInformation = giveInformation;
    }

    public String getUserPhoto() {
        return UserPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        UserPhoto = userPhoto;
    }

    public String getUserSex() {
        return UserSex;
    }

    public void setUserSex(String userSex) {
        UserSex = userSex;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public Integer getState() {
        return State;
    }

    public void setState(Integer state) {
        State = state;
    }
}
