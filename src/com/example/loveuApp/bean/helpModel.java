package com.example.loveuApp.bean;

/**
 * Created by dy on 2016/7/26.
 */
public class helpModel {

    private Integer HelpId;
    private Integer UserId;
    private Integer HelpMoney;
    private String DownTime;
    private String HelpInformation;
    private Integer GetUser;
    private Integer State;
    private Integer Finish;
    private String UserPhoto;
    private String NickName;

    public String getDownTime() {
        return DownTime;
    }

    public void setDownTime(String downTime) {
        DownTime = downTime;
    }

    public Integer getFinish() {
        return Finish;
    }

    public void setFinish(Integer finish) {
        Finish = finish;
    }

    public Integer getGetUser() {
        return GetUser;
    }

    public void setGetUser(Integer getUser) {
        GetUser = getUser;
    }

    public Integer getHelpId() {
        return HelpId;
    }

    public void setHelpId(Integer helpId) {
        HelpId = helpId;
    }

    public String getHelpInformation() {
        return HelpInformation;
    }

    public void setHelpInformation(String helpInformation) {
        HelpInformation = helpInformation;
    }

    public Integer getHelpMoney() {
        return HelpMoney;
    }

    public void setHelpMoney(Integer helpMoney) {
        HelpMoney = helpMoney;
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
