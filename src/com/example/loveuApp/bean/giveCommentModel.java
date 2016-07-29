package com.example.loveuApp.bean;

/**
 * Created by dy on 2016/7/26.
 */
public class GiveCommentModel {

    private Integer CommentId;
    private Integer UserId;
    private String CommentInformation;
    private Integer GiveId;
    private String msg;

    public GiveCommentModel(){

    }

    public GiveCommentModel(Integer commentId, String commentInformation, Integer giveId, Integer userId, String msg) {
        CommentId = commentId;
        CommentInformation = commentInformation;
        GiveId = giveId;
        UserId = userId;
        this.msg=msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCommentId() {
        return CommentId;
    }

    public void setCommentId(Integer commentId) {
        CommentId = commentId;
    }

    public String getCommentInformation() {
        return CommentInformation;
    }

    public void setCommentInformation(String commentInformation) {
        CommentInformation = commentInformation;
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
}
