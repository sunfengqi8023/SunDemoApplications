package com.mr_sun.myapplication.login.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mr_sun.myapplication.R;
import com.mr_sun.myapplication.login.presenter.ILoginPresenter;
import com.mr_sun.myapplication.login.presenter.impl.ILoginPresenterImpl;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements ILoginView, View.OnClickListener {
    private static final String TAG = "MainActivity";

    private EditText et_username;//用户名
    private EditText et_password;//密码
    private Button btn_ok;//登录
    private TextView tv_message;//显示反馈的信息

    private ILoginPresenter loginPresenter;//执行者

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    public void initView(){
        et_username = (EditText)findViewById(R.id.et_username);
        et_password = (EditText)findViewById(R.id.et_password);
        btn_ok = (Button)findViewById(R.id.btn_ok);
        tv_message = (TextView)findViewById(R.id.tv_message);

        btn_ok.setOnClickListener(this);

        loginPresenter = new ILoginPresenterImpl(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_ok:
                //login
                String name = et_username.getText().toString();
                String pwd = et_password.getText().toString();
                loginPresenter.doLogin(name,pwd);
                break;
            default:
                break;
        }
    }

    @Override
    public void onLoginResult(Boolean result, String code) {
        tv_message.setText(code);
    }

    @Override
    public void onSetProgressBarVisibility(int visibility) {

    }

    @Override
    public void toastMsg(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }
}
