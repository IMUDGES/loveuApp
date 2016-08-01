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
    private Integer state;
    private String msg;

    public Integer getstate() {
        return state;
    }

    public void setstate(Integer state) {
        this.state = state;
    }



    public helpModel(){

    }

    public helpModel(String downTime, Integer finish, Integer getUser, Integer helpId, String helpInformation,
                     Integer helpMoney, String msg, Integer state, Integer userId) {
        DownTime = downTime;
        Finish = finish;
        GetUser = getUser;
        HelpId = helpId;
        HelpInformation = helpInformation;
        HelpMoney = helpMoney;
        this.msg = msg;
        State = state;
        UserId = userId;
    }

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
        State = state;
    }

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }
}
