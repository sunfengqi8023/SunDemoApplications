package demos.sun.com.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import org.ksoap2.serialization.SoapObject;

public class MainActivity extends Activity implements OnClickListener {
	private final String TAG = MainActivity.class.getSimpleName();

	private TextView tv_info;// 显示返回信息
	private Button btn_ok;// 测试点击按钮
	private Button btn_ok_http;// 测试点击按钮


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();

	}

	public void initView() {
		tv_info = (TextView) findViewById(R.id.tv_info);
		btn_ok = (Button) findViewById(R.id.btn_ok);
		btn_ok_http = (Button) findViewById(R.id.btn_ok_http);

		btn_ok.setOnClickListener(this);
		btn_ok_http.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_ok:
			
			DialogUtils.startProgressDialog(MainActivity.this, "正在测试...");
			
			WebServiceUtil.login("34134123", "1234123412341", new WebServiceUtil.WebServiceCallBack() {
				
				@Override
				public void callBack(SoapObject result) {

					Log.v(TAG,"result:"+result.toString());
					final Object obj = result.getProperty(0);
						JsonUtils.parseUstLogin(obj.toString(), new JsonUtils.ResultCallBack() {
							
							@Override
							public void callBackFailed(int errcode, final String result) {
								// TODO Auto-generated method stub
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										tv_info.setText(result);
										DialogUtils.stopProgressDialog();
									}
								});
							}
							
							@Override
							public void callBack(final Object result) {
								// TODO Auto-generated method stub
								
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										tv_info.setText(result.toString());
										DialogUtils.stopProgressDialog();
									}
								});
							}
						});
				}

				@Override
				public void callBackFailed(final String error) {
					// TODO Auto-generated method stub
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							tv_info.setText(error);
							DialogUtils.stopProgressDialog();
						}
					});
				}
			});
			break;
			case R.id.btn_ok_http:
				new Thread(new Runnable() {
					@Override
					public void run() {
						String sss = testHttpclient.httpGet("https://www.huajinzaixian.com/Index_toHome_Static.action");
						Log.v(TAG,"wwww:"+sss);
					}
				}).start();
				break;
		default:
			break;
		}
		
	}
}
