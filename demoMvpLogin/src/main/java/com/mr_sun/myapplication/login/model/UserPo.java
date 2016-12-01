package com.mr_sun.myapplication.login.model;

/**
 * Created by Mr_Sun on 2016/11/26.
 */

public class UserPo {
    private static final String TAG = "UserPo";

    private String userName = "";//用户名
    private String userType = "";//用户类型

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
