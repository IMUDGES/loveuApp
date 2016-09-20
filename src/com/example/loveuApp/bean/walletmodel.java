package com.example.loveuApp.bean;

/**
 * Created by caolu on 2016/9/20.
 */
public class walletmodel {

    private userModel data;
    private Integer state;
    private Integer isjwxt;

    public Integer getIsjwxt() {
        return isjwxt;
    }

    public void setIsjwxt(Integer isjwxt) {
        this.isjwxt = isjwxt;
    }

    public userModel getData() {
        return data;
    }

    public void setData(userModel data) {
        this.data = data;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
