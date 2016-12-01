package demos.sun.com.myapplication;

import android.app.AlertDialog;
import android.content.Context;

/**
 * @author xf
 * 
 * 
 *         对话框工具类
 * */
public class DialogUtils {
	protected static AlertDialog mAlertDialog;
	private static CustomProgressDialog progressDialog = null;

	public static AlertDialog showAlertDialog(Context context, String TitleID,
			String Message) {
		mAlertDialog = new AlertDialog.Builder(context).setTitle(TitleID)
				.setMessage(Message).show();
		mAlertDialog.setCanceledOnTouchOutside(false);
		return mAlertDialog;
	}

	public static void startProgressDialog(Context context, String msg) {
		if (progressDialog == null) {
			progressDialog = CustomProgressDialog.createDialog(context);
			progressDialog.setMessage(msg);
		}
        try{
        	progressDialog.show();
        }catch(Exception e){
        	e.printStackTrace();
        }
		
	}

	public static void stopProgressDialog() {
		if (progressDialog != null) {
			progressDialog.dismiss();
			progressDialog = null;
		}
	}

}
