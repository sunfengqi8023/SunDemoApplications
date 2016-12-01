package com.mr_sun.demorxjavatry.mvp.api;

import com.mr_sun.demorxjavatry.mvp.model.GankModel;

import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Mr_Sun on 2016/12/1.
 */

public interface GankApi {
    @GET("api/data/Android/10/{page}")
    rx.Observable<GankModel> getAndroidInfoWithBean(@Path("page") int page);
}
