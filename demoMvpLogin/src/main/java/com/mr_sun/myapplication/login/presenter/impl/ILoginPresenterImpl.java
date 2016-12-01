package com.mr_sun.myapplication.login.presenter.impl;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.mr_sun.myapplication.login.utils.WebServiceUtil;
import com.mr_sun.myapplication.login.view.ILoginView;

import org.ksoap2.serialization.SoapObject;

/**
 * Created by Mr_Sun on 2016/11/26.
 */

public class ILoginPresenterImpl implements com.mr_sun.myapplication.login.presenter.ILoginPresenter {

    ILoginView loginView;

    Handler handler;

    public ILoginPresenterImpl(ILoginView loginView) {
        this.loginView = loginView;
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void doLogin(String name, String passwd) {
        if(!checkEmptyStr(name,passwd)){
            return;
        }
        WebServiceUtil.login(name, "f379eaf3c831b04de153469d1bec345e", new WebServiceUtil.WebServiceCallBack() {

            @Override
            public void callBack(final SoapObject result) {
                final Object obj = result.getProperty(0);
                final String getResult = obj.toString();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loginView.onLoginResult(true,getResult);
                    }
                }, 3000);

            }

            @Override
            public void callBackFailed(final String error) {
                // TODO Auto-generated method stub
                loginView.onLoginResult(true,"登录失败了");
            }
        });
    }

    @Override
    public void setProgressBarVisiblity(int visiblity) {

    }

    /**
     * 检查空字符串得
     * @param name
     * @param passwd
     */
    public boolean checkEmptyStr(String name, String passwd){
        boolean flag = true;
        if(TextUtils.isEmpty(name)||TextUtils.isEmpty(passwd)){
            loginView.toastMsg("用户名密码不能为空！");
            flag = flag;
        }
        return flag;
    }
}
