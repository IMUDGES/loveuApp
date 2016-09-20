package com.example.loveuApp.left.model;

import com.example.loveuApp.bean.foodModel;
import com.example.loveuApp.bean.helpModel;

import java.util.List;

/**
 * Created by dy on 2016/9/20.
 */
public class HelpModelAll {

    private Integer num;
    private List<helpModel> helplist;

    public List<helpModel> getHelplist() {
        return helplist;
    }

    public void setHelplist(List<helpModel> helplist) {
        this.helplist = helplist;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
