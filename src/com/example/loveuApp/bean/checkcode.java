package com.example.loveuApp.bean;

/**
 * Created by dy on 2016/7/26.
 */
public class Checkcode {


    private Integer CheckId;
    private String UserPhone;
    private Integer CheckCode;
    private String msg;
    private Integer state;

    public Checkcode(){

    }

    public Checkcode(Integer checkCode, Integer checkId, String msg, Integer state, String userPhone) {
        CheckCode = checkCode;
        CheckId = checkId;
        this.msg = msg;
        this.state = state;
        UserPhone = userPhone;
    }

    public Integer getCheckCode() {
        return CheckCode;
    }

    public void setCheckCode(Integer checkCode) {
        CheckCode = checkCode;
    }

    public Integer getCheckId() {
        return CheckId;
    }

    public void setCheckId(Integer checkId) {
        CheckId = checkId;
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

    public String getUserPhone() {
        return UserPhone;
    }

    public void setUserPhone(String userPhone) {
        UserPhone = userPhone;
    }
}
