package com.example.loveuApp.bean;

/**
 * Created by dy on 2016/7/26.
 */
public class paiCommentModel {

    private Integer CommentId;
    private Integer UserId;
    private String CommentInformation;
    private Integer PaiId;
    private String NickName;
    private String UserPhoto;

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }


    public String getUserPhoto() {
        return UserPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        UserPhoto = userPhoto;
    }

    public paiCommentModel(){

    }

    public paiCommentModel(Integer commentId, String commentInformation, String msg, Integer paiId, Integer userId) {
        CommentId = commentId;
        CommentInformation = commentInformation;
        PaiId = paiId;
        UserId = userId;
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

    public Integer getPaiId() {
        return PaiId;
    }

    public void setPaiId(Integer paiId) {
        PaiId = paiId;
    }

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }
}
