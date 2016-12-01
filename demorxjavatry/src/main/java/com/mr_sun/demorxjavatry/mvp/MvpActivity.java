package com.mr_sun.demorxjavatry.mvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextClock;
import android.widget.TextView;

import com.mr_sun.demorxjavatry.R;
import com.mr_sun.demorxjavatry.mvp.presenter.Ipresenter;
import com.mr_sun.demorxjavatry.mvp.presenter.IpresenterImpl;
import com.mr_sun.demorxjavatry.mvp.view.IGankAndroidInfo;

public class MvpActivity extends AppCompatActivity implements IGankAndroidInfo {
    private static final String TAG = "MvpActivity";

    private TextView tv_mesage;

    private IpresenterImpl ipresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);

        initviw();
        ipresenter = new IpresenterImpl(this);
        ipresenter.getAndSetMessage();
    }

    private void initviw(){
        tv_mesage = (TextView)findViewById(R.id.tv_mesage);
    }

    @Override
    public void displayMessage(String message) {
        Log.v(TAG,"displayMessage");
        if(TextUtils.isEmpty(message)){
            tv_mesage.setText("没有获取任何信息");
        }else{
            tv_mesage.setText(message);
        }
    }
}
