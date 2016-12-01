package com.mr_sun.demorxjavatry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mr_sun.demorxjavatry.mvp.MvpActivity;
import com.mr_sun.demorxjavatry.retrofit.GnakApi;
import com.mr_sun.demorxjavatry.retrofit.login;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Scheduler;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private String baseUrl = "http://gank.io/";

    private Button btn_tijiao;//提交按钮
    private Button btn_tijiao_gson;//提交按钮+gson
    private Button btn_tijiao_rxjava;//提交按钮+rxjava
    private TextView tv_message;//显示返回的数据
    private Button btn_tijiao_mvp ;//跳转到mvp的实现模式

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initview();
    }

    private void initview() {
        btn_tijiao = (Button) findViewById(R.id.btn_tijiao);
        tv_message = (TextView) findViewById(R.id.tv_message);
        btn_tijiao_gson = (Button) findViewById(R.id.btn_tijiao_gson);
        btn_tijiao_rxjava = (Button) findViewById(R.id.btn_tijiao_rxjava);
        btn_tijiao_mvp = (Button)findViewById(R.id.btn_tijiao_mvp);

        btn_tijiao.setOnClickListener(this);
        btn_tijiao_gson.setOnClickListener(this);
        btn_tijiao_rxjava.setOnClickListener(this);
        btn_tijiao_mvp.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_tijiao:
                //onlyRetrofit
                //提交网络访问接口数据
                //addConverterFactory方法表示需要用什么转换器来解析返回值，
                // GsonConverterFactory.create()表示调用Gson库来解析json返回值
                Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                        //.addConverterFactory(GsonConverterFactory.create())
                        .build();
                GnakApi api = retrofit.create(GnakApi.class);
                Call<ResponseBody> call = api.getAndroidInfo();
                //画外音，excute是同步执行，必须在非主线程中使用
                //call.execute().body();
                //enqueue 异步执行
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        String message = null;
                        try {
                            message = response.body().string();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        tv_message.setText("请求数据成功：" + message);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        tv_message.setText("请求失败了");
                    }
                });
                break;
            case R.id.btn_tijiao_gson:
                //写给自己
                /*Gson gson = new GsonBuilder()
                        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                        .create();
                //传入自定义的gson
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://api.nuuneoi.com/base/")
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();*/
            Retrofit retrofit2 = new Retrofit.Builder().baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
                GnakApi api2 = retrofit2.create(GnakApi.class);
                Call<GankBean> call2 = api2.getAndroidInfoWithBean();
                call2.enqueue(new Callback<GankBean>() {
                    @Override
                    public void onResponse(Call<GankBean> call, Response<GankBean> response) {
                        GankBean.ResultsBean bean = response.body().getResults().get(0);
                        tv_message.setText(
                                "_id:" + bean.get_id() + "\n"
                                        + "createdAt：" + bean.getCreatedAt() + "\n"
                                        + "desc：" + bean.getDesc() + "\n"
                                        + "images:" + bean.getImages() + "\n"
                                        + "publishedAt:" + bean.getPublishedAt() + "\n"
                                        + "source" + bean.getSource() + "\n"
                                        + "type:" + bean.getType() + "\n"
                                        + "url: " + bean.getUrl() + "\n"
                                        + "who:" + bean.getWho());

                    }

                    @Override
                    public void onFailure(Call<GankBean> call, Throwable t) {
                        tv_message.setText("请求失败了");
                    }
                });
                break;
            case R.id.btn_tijiao_rxjava:
                Retrofit retrofit3 = new Retrofit.Builder().baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();
                final login api3 = retrofit3.create(login.class);
                User user = new User("liuguilin", "748778890");
                api3.loginForRX(user).subscribeOn(Schedulers.io())
                        .flatMap(new Func1<User, Observable<User>>() {
                    @Override
                    public Observable<User> call(User user) {
                        return api3.getUserForRX(user.getUser_id());
                    }
                }).subscribe(new Action1<User>() {
                    @Override
                    public void call(User user) {
                        Toast.makeText(MainActivity.this, "name:" + user.getName(), Toast.LENGTH_SHORT).show();
                    }
                });

                break;
            case R.id.btn_tijiao_mvp:
                Intent intent = new Intent(this, MvpActivity.class);
                this.startActivity(intent);
                break;
            default:
                break;
        }
    }
}
