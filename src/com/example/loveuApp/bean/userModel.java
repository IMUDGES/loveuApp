package com.example.loveuApp.bean;

/**
 * Created by dy on 2016/7/26.
 */
public class userModel {


    private Integer UserId;
    private String UserPhone;
    private String PassWord;
    private String NickName;
    private String TrueName;
    private Integer UserSex;
    private String UserGrade;
    private String UserPhoto;
    private String SecretKey;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String UserMajor;
    private String state;
    private String msg;

    public userModel(){

    }

    public userModel(String msg, String nickName, String passWord, String secretKey,
                     String trueName, String userGrade, Integer userId, String userMajor, String userPhone, String userPhoto, Integer userSex) {
        this.msg = msg;
        NickName = nickName;
        PassWord = passWord;
        SecretKey = secretKey;
        TrueName = trueName;
        UserGrade = userGrade;
        UserId = userId;
        UserMajor = userMajor;
        UserPhone = userPhone;
        UserPhoto = userPhoto;
        UserSex = userSex;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }

    public String getSecretKey() {
        return SecretKey;
    }

    public void setSecretKey(String secretKey) {
        SecretKey = secretKey;
    }

    public String getTrueName() {
        return TrueName;
    }

    public void setTrueName(String trueName) {
        TrueName = trueName;
    }

    public String getUserGrade() {
        return UserGrade;
    }

    public void setUserGrade(String userGrade) {
        UserGrade = userGrade;
    }

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }

    public String getUserMajor() {
        return UserMajor;
    }

    public void setUserMajor(String userMajor) {
        UserMajor = userMajor;
    }

    public String getUserPhone() {
        return UserPhone;
    }

    public void setUserPhone(String userPhone) {
        UserPhone = userPhone;
    }

    public String getUserPhoto() {
        return UserPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        UserPhoto = userPhoto;
    }

    public Integer getUserSex() {
        return UserSex;
    }

    public void setUserSex(Integer userSex) {
        UserSex = userSex;
    }
}
