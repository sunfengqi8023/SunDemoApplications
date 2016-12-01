package demos.sun.com.myapplication;

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {
	private final String TAG = JsonUtils.class.getSimpleName();

	public interface ResultCallBack {
		public void callBack(Object result);
		public void callBackFailed(int errcode, String result);
	}

	public static void parseUstLogin(String result, ResultCallBack callBack) {

		if (TextUtils.isEmpty(result)) {
			callBack.callBackFailed(404, "");
			return;
		}
		try {
			callBack.callBack(result);
		} catch (Exception e) {
			callBack.callBackFailed(500, e.getMessage().toString());
		}
	}
}
