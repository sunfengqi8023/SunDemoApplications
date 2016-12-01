package demos.sun.com.myapplication;

import android.app.Application;

import java.io.IOException;
import java.nio.Buffer;

import javax.net.ssl.SSLSocketFactory;

import demos.sun.com.myapplication.singleAuth.SSLUtils;

/**
 * Created by Mr_Sun on 2016/11/18.
 */

public class sunApplication extends Application {
    private static final String TAG = "sunApplication";


    public static SSLSocketFactory socketFactory = null;

    @Override
    public void onCreate() {
        super.onCreate();
        //方法1 直接读取cer文件
        if (socketFactory == null) {
            try {
                socketFactory = SSLUtils.getSSLSocketFactory(getApplicationContext().getAssets().open("sun_server.cer"));
                //方法2 将文件转换成字符串以后转换成inputStram
                //socketFactory = SSLUtils.getSSLSocketFactory(SSLUtils.String2InputStream("证书字符串"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
