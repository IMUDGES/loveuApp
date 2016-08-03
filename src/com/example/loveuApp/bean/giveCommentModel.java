package com.example.loveuApp.bean;

/**
 * Created by dy on 2016/7/26.
 */
public class giveCommentModel {
    private Integer UserId;
    private String CommentInformation;
    private String UserSex;
    private String NickName;
    private String UserPhoto;
    public giveCommentModel(){
    }

    public giveCommentModel(Integer userId, String commentInformation, String userSex, String nickName, String userPhoto) {
        UserId = userId;
        CommentInformation = commentInformation;
        UserSex = userSex;
        NickName = nickName;
        UserPhoto = userPhoto;
    }

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }

    public String getCommentInformation() {
        return CommentInformation;
    }

    public void setCommentInformation(String commentInformation) {
        CommentInformation = commentInformation;
    }

    public String getUserSex() {
        return UserSex;
    }

    public void setUserSex(String userSex) {
        UserSex = userSex;
    }

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
}
