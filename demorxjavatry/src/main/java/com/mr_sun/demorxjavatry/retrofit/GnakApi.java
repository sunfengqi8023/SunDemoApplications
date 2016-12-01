package com.mr_sun.demorxjavatry.retrofit;

import com.mr_sun.demorxjavatry.GankBean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Mr_Sun on 2016/11/30.
 * 学习http://blog.csdn.net/qq_26787115/article/details/53034267
 * 根据blog中提到的地址，学习访问接口
 * 接口地址：http://gank.io/api/data/Android/10/1
 */

public interface GnakApi {
    @GET("api/data/Android/10/1")
    Call<ResponseBody> getAndroidInfo();

    @GET("api/data/Android/10/1")
    Call<GankBean> getAndroidInfoWithBean();

    //{} 用花括号包裹的是动态字符串 用@Path进行注解，参数名字必须一致
    @GET("api/data/Android/10/{page}")
    Call<GankBean> getAndroidInfoWithBean(@Path("page") int page);

    //AlexApp/login?phone=13146008025&pwd=123456
    @GET("AlexApp/login")
    Call<GankBean> getAndroidInfoWithBean(@Query("phone") String phone,@Query("pwd")String pwd);
    //与程序无关，记录get参数的拼接
    //@GET("onebox/weather/query?")
    //Call<WeatherDataBean> getWeather(@QueryMap Map<String, String> params);

    //与程序无关，mark post请求
    //@POST("user/new")
   //Call<Result> postUser(@Body User user);

    //提交表单
    //@POST("user/edit")
    //Call<Result> editUser(@Field("id") int id, @Field("name") String name);

    //修改header
    //@Headers("Cache-Control: max-age=640000")
    //@GET("widget/list")
    //Call<List<Widget>> widgetList();

}
