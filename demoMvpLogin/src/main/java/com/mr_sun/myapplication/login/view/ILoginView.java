package com.mr_sun.myapplication.login.view;

/**
 * Created by Mr_Sun on 2016/11/26.
 */

public interface ILoginView {

    public void onLoginResult(Boolean result, String code);
    public void onSetProgressBarVisibility(int visibility);

    public void toastMsg(String msg);

}
