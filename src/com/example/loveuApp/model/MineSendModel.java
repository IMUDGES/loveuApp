package com.example.loveuApp.model;

import com.example.loveuApp.bean.*;

import java.util.List;

/**
 * Created by caolu on 2016/8/10.
 */
public class MineSendModel {

    private Integer num;
    private Integer state;
    private String msg;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    private int myissuefood_num;
    private int myissuefood_overdue_num;

    private int myissuehelp_num;
    private int myissuehelp_overdue_num;

    private int myissuepai_num;
    private int myissuepai_overdue_num;

    private int myissuegive_num;
    private int myissuegive_overdue_num;

    private int myissuexue_num;
    private int myissuexue_overdue_num;

    private int myissuerun_num;
    private int myissuerun_overdue_num;

    private List<foodModel> myissuefood;
    private List<foodModel> myissuefood_overdue;

    private List<paiModel> myissuepai;
    private List<paiModel> myissuepai_overdue;

    private List<helpModel> myissuehelp;
    private List<helpModel> myissuehelp_overdue;

    private List<giveModel> myissuegive;
    private List<giveModel> myissuegive_overdue;

    private List<xueModel> myissuexue;
    private List<xueModel> myissuexue_overdue;

    private List<runModel> myissuerun;
    private List<runModel> myissuerun_overdue;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getMyissuefood_num() {
        return myissuefood_num;
    }

    public List<xueModel> getMyissuexue_overdue() {
        return myissuexue_overdue;
    }

    public void setMyissuexue_overdue(List<xueModel> myissuexue_overdue) {
        this.myissuexue_overdue = myissuexue_overdue;
    }

    public List<runModel> getMyissuerun_overdue() {
        return myissuerun_overdue;
    }

    public void setMyissuerun_overdue(List<runModel> myissuerun_overdue) {
        this.myissuerun_overdue = myissuerun_overdue;
    }

    public List<runModel> getMyissuerun() {
        return myissuerun;
    }

    public void setMyissuerun(List<runModel> myissuerun) {
        this.myissuerun = myissuerun;
    }

    public void setMyissuefood_num(int myissuefood_num) {
        this.myissuefood_num = myissuefood_num;
    }

    public int getMyissuefood_overdue_num() {
        return myissuefood_overdue_num;
    }

    public void setMyissuefood_overdue_num(int myissuefood_overdue_num) {
        this.myissuefood_overdue_num = myissuefood_overdue_num;
    }

    public int getMyissuehelp_num() {
        return myissuehelp_num;
    }

    public void setMyissuehelp_num(int myissuehelp_num) {
        this.myissuehelp_num = myissuehelp_num;
    }

    public int getMyissuehelp_overdue_num() {
        return myissuehelp_overdue_num;
    }

    public void setMyissuehelp_overdue_num(int myissuehelp_overdue_num) {
        this.myissuehelp_overdue_num = myissuehelp_overdue_num;
    }

    public int getMyissuepai_num() {
        return myissuepai_num;
    }

    public void setMyissuepai_num(int myissuepai_num) {
        this.myissuepai_num = myissuepai_num;
    }

    public int getMyissuegive_num() {
        return myissuegive_num;
    }

    public void setMyissuegive_num(int myissuegive_num) {
        this.myissuegive_num = myissuegive_num;
    }

    public int getMyissuepai_overdue_num() {
        return myissuepai_overdue_num;
    }

    public void setMyissuepai_overdue_num(int myissuepai_overdue_num) {
        this.myissuepai_overdue_num = myissuepai_overdue_num;
    }

    public int getMyissuegive_overdue_num() {
        return myissuegive_overdue_num;
    }

    public void setMyissuegive_overdue_num(int myissuegive_overdue_num) {
        this.myissuegive_overdue_num = myissuegive_overdue_num;
    }

    public int getMyissuexue_num() {
        return myissuexue_num;
    }

    public void setMyissuexue_num(int myissuexue_num) {
        this.myissuexue_num = myissuexue_num;
    }

    public int getMyissuexue_overdue_num() {
        return myissuexue_overdue_num;
    }

    public void setMyissuexue_overdue_num(int myissuexue_overdue_num) {
        this.myissuexue_overdue_num = myissuexue_overdue_num;
    }

    public int getMyissuerun_num() {
        return myissuerun_num;
    }

    public void setMyissuerun_num(int myissuerun_num) {
        this.myissuerun_num = myissuerun_num;
    }

    public int getMyissuerun_overdue_num() {
        return myissuerun_overdue_num;
    }

    public void setMyissuerun_overdue_num(int myissuerun_overdue_num) {
        this.myissuerun_overdue_num = myissuerun_overdue_num;
    }

    public List<foodModel> getMyissuefood() {
        return myissuefood;
    }

    public void setMyissuefood(List<foodModel> myissuefood) {
        this.myissuefood = myissuefood;
    }

    public List<foodModel> getMyissuefood_overdue() {
        return myissuefood_overdue;
    }

    public void setMyissuefood_overdue(List<foodModel> myissuefood_overdue) {
        this.myissuefood_overdue = myissuefood_overdue;
    }

    public List<paiModel> getMyissuepai() {
        return myissuepai;
    }

    public void setMyissuepai(List<paiModel> myissuepai) {
        this.myissuepai = myissuepai;
    }

    public List<paiModel> getMyissuepai_overdue() {
        return myissuepai_overdue;
    }

    public void setMyissuepai_overdue(List<paiModel> myissuepai_overdue) {
        this.myissuepai_overdue = myissuepai_overdue;
    }

    public List<helpModel> getMyissuehelp() {
        return myissuehelp;
    }

    public void setMyissuehelp(List<helpModel> myissuehelp) {
        this.myissuehelp = myissuehelp;
    }

    public List<helpModel> getMyissuehelp_overdue() {
        return myissuehelp_overdue;
    }

    public void setMyissuehelp_overdue(List<helpModel> myissuehelp_overdue) {
        this.myissuehelp_overdue = myissuehelp_overdue;
    }

    public List<giveModel> getMyissuegive() {
        return myissuegive;
    }

    public void setMyissuegive(List<giveModel> myissuegive) {
        this.myissuegive = myissuegive;
    }

    public List<giveModel> getMyissuegive_overdue() {
        return myissuegive_overdue;
    }

    public void setMyissuegive_overdue(List<giveModel> myissuegive_overdue) {
        this.myissuegive_overdue = myissuegive_overdue;
    }

    public List<xueModel> getMyissuexue() {
        return myissuexue;
    }

    public void setMyissuexue(List<xueModel> myissuexue) {
        this.myissuexue = myissuexue;
    }
}
