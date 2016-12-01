package com.mr_sun.demorxjavatry.mvp.view;

/**
 * Created by Mr_Sun on 2016/12/1.
 * view的基础接口
 */

public interface IBaseView<T> {
    void setPresenter(T presenter);
}
