package com.example.loveuApp.bean;

/**
 * Created by dy on 2016/7/26.
 */
public class runModel {

    private Integer RunId;
    private Integer UserId;
    private String RunInformation;
    private Integer GetUser;
    private String RunTime;
    private Integer State;
    private String RunArea;
    private String msg;
    private String UserPhoto;
    private String NickName;
    private Integer state;
    private int UserSex;
    private int num;

    public void setstate(Integer state){
        this.state = state;
    }
    public Integer getstate(){
        return state;
    }
    public String getUserPhoto() {
        return UserPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        UserPhoto = userPhoto;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public int getUserSex() {
        return UserSex;
    }

    public void setUserSex(int userSex) {
        UserSex = userSex;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public runModel(){

    }

    public runModel(Integer getUser, String msg, String runArea, Integer runId,
                    String runInformation, String runTime, Integer state, Integer userId) {
        GetUser = getUser;
        this.msg = msg;
        RunArea = runArea;
        RunId = runId;
        RunInformation = runInformation;
        RunTime = runTime;
        State = state;
        UserId = userId;
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

    public String getRunArea() {
        return RunArea;
    }

    public void setRunArea(String runArea) {
        RunArea = runArea;
    }

    public Integer getRunId() {
        return RunId;
    }

    public void setRunId(Integer runId) {
        RunId = runId;
    }

    public String getRunInformation() {
        return RunInformation;
    }

    public void setRunInformation(String runInformation) {
        RunInformation = runInformation;
    }

    public String getRunTime() {
        return RunTime;
    }

    public void setRunTime(String runTime) {
        RunTime = runTime;
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
