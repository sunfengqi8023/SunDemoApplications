package sundemos.dhcc.com.loading;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_runing_man);
        //android.R.style.Theme_DeviceDefault
    }

    /**
     * 显示美团进度对话框
     * @param v
     */
    public void showmeidialog(View v){
        CustomProgressDialog dialog =new CustomProgressDialog(this, "正在加载中",R.anim.frame);
        dialog.show();
    }
    /**
     * 显示顺丰快递员进度对话框
     * @param v
     */
    public void showsfdialog(View v){
        CustomProgressDialog dialog =new CustomProgressDialog(this, "正在加载中",R.anim.frame2);
        dialog.show();
    }
}
