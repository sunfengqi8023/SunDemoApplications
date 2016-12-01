package com.mr_sun.demorxjavatry.retrofit;

import com.mr_sun.demorxjavatry.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Mr_Sun on 2016/11/30.
 */

public interface login {
    //表单提交
    /*@POST("user/login")
    Call<User> login(@Field("username") String user, @Field("password") String password);

    //通过id获取用户的信息
    @GET("user/info")
    Call<User> getUser(@Query("id") String id);*/

    //以下为rxjava 设计的接口
    @POST("user/login")
    rx.Observable<User> loginForRX(@Body User user);

    @GET("user/info")
    rx.Observable<User> getUserForRX(@Query("id") String id);
}
