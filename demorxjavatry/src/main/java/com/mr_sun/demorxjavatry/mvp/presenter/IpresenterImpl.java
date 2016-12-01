package com.mr_sun.demorxjavatry.mvp.presenter;

import android.util.Log;

import com.mr_sun.demorxjavatry.mvp.api.GankApi;
import com.mr_sun.demorxjavatry.mvp.model.GankModel;
import com.mr_sun.demorxjavatry.mvp.view.IGankAndroidInfo;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Mr_Sun on 2016/12/1.
 */

public class IpresenterImpl implements Ipresenter {
    private static final String TAG = "IpresenterImpl";
    private String baseUrl = "http://gank.io/";

    IGankAndroidInfo iGankAndroidInfo;

    Retrofit retrofit;
    GankApi gankapi;

    public IpresenterImpl(IGankAndroidInfo view) {
        iGankAndroidInfo = view;
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            gankapi = retrofit.create(GankApi.class);
        }
    }

    @Override
    public void getAndSetMessage() {
        Log.v(TAG, "getAndSetMessage");
        gankapi.getAndroidInfoWithBean(1)
                //Schedulers.immediate(): 直接在当前线程运行，相当于不指定线程。这是默认的 Scheduler。
                //Schedulers.newThread(): 总是启用新线程，并在新线程执行操作。
                // Schedulers.io(): I/O 操作（读写文件、读写数据库、网络信息交互等）所使用的 Scheduler。行为模式和 newThread() 差不多，区别在于 io() 的内部实现是是用一个无数量上限的线程池，可以重用空闲的线程，因此多数情况下 io() 比 newThread() 更有效率。不要把计算工作放在 io() 中，可以避免创建不必要的线程。
                //Schedulers.computation(): 计算所使用的 Scheduler。这个计算指的是 CPU 密集型计算，即不会被 I/O 等操作限制性能的操作，例如图形的计算。这个 Scheduler 使用的固定的线程池，大小为 CPU 核数。不要把 I/O 操作放在 computation() 中，否则 I/O 操作的等待时间会浪费 CPU。
                // 另外， Android 还有一个专用的 AndroidSchedulers.mainThread()，它指定的操作将在 Android 主线程运行。
                .subscribeOn(Schedulers.io())
                //指定回调在哪执行
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<GankModel>() {
                    @Override
                    public void call(GankModel gankModel) {
                        Log.v(TAG, "getAndroidInfoWithBean");
                        GankModel.ResultsBean bean = gankModel.getResults().get(0);
                        iGankAndroidInfo.displayMessage(bean.get_id() + "\n" + bean.getWho());
                    }
                });

    }
}
