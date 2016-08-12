package com.example.loveuApp.bean;

import java.io.Serializable;

/**
 * Created by 1111 on 2016/8/11.
 */
public class userSetModel implements Serializable {
    private Integer UserSex;
    private String NickName;
    private Integer isjwxt;

    public userSetModel() {
    }

    public userSetModel(int userSex, String nickName, Integer isjwxt) {
        UserSex = userSex;
        NickName = nickName;
        this.isjwxt = isjwxt;
    }

    public int getUserSex() {
        return UserSex;
    }

    public void setUserSex(int userSex) {
        UserSex = userSex;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public Integer getIsjwxt() {
        return isjwxt;
    }

    public void setIsjwxt(Integer isjwxt) {
        this.isjwxt = isjwxt;
    }

}
