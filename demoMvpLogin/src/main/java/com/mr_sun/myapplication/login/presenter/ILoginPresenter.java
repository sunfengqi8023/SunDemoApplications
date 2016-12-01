package com.mr_sun.myapplication.login.presenter;

/**
 * Created by Mr_Sun on 2016/11/26.
 * 注册的presenter,实现具体的业务逻辑接口
 */

public interface ILoginPresenter {
    static final String TAG = "ILoginPresenterImpl";

    void doLogin(String name, String passwd);
    void setProgressBarVisiblity(int visiblity);

}
