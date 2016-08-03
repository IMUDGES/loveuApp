package com.example.loveuApp.bean;

/**
 * Created by dy on 2016/7/26.
 */
public class giveModel {

    private Integer GiveId;
    private Integer UserId;
    private String GiveInformation;
    private String GiveImage;
    private String UserPhoto;
    private String UserSex;
    private String NickName;
    private Integer State;
    public giveModel(){

    }

    public giveModel(Integer giveId, Integer userId, String giveInformation,
                     String giveImage,  String userPhoto,
                     String userSex, String nickName, Integer state) {
        GiveId = giveId;
        UserId = userId;
        GiveInformation = giveInformation;
        GiveImage = giveImage;
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

    public String getGiveImage() {
        return GiveImage;
    }

    public void setGiveImage(String giveImage) {
        GiveImage = giveImage;
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
