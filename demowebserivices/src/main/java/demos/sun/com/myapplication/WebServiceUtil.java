package demos.sun.com.myapplication;

import android.text.TextUtils;

import org.json.JSONException;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.MarshalBase64;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.ksoap2.transport.HttpsServiceConnectionSE;
import org.ksoap2.transport.HttpsTransportSE;
import org.kxml2.kdom.Element;
import org.kxml2.kdom.Node;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class WebServiceUtil {
    // 登录注册的nameSpace
    public static String loginPoint = "http://suntuhao:8088" + "/services/loginService";

    public static String nameSpace = "http://webservice.im.boyuyun.com/";

    /**
     * 回调函数接口声明
     */
    public interface WebServiceCallBack {
        public void callBack(SoapObject result);

        public void callBackFailed(String error);
    }

    //webservices 接口访问方法
    public static void webservicesHelp(final String method, String nameSpace, String endPoint, HashMap<String, Object> properties,
                                       final WebServiceCallBack webServiceCallBack) {

        SoapObject rpc = new SoapObject(nameSpace, method);
        if (properties != null) {
            for (Iterator<Map.Entry<String, Object>> it = properties.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry<String, Object> entry = it.next();
                rpc.addProperty(entry.getKey(), entry.getValue());
            }
        }

        final SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
        new MarshalBase64().register(envelope);
        //添加的额外的认证头部
        //envelope.headerOut = getHeader(nameSpace);
        envelope.bodyOut = rpc;
        envelope.dotNet = false;
        envelope.setOutputSoapObject(rpc);

        //重新设置了超时时间为15s
        //final HttpTransportSE transport = new HttpTransportSE(endPoint, 15000);
        final HttpsTransportSE transport = new HttpsTransportSE("16m09v9725.iask.in", 8088, "/services/loginService", 15000);
        try {
            ((HttpsServiceConnectionSE) transport.getServiceConnection()).setSSLSocketFactory(sunApplication.socketFactory);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Runnable runable = new Runnable() {
            @Override
            public void run() {
                SoapObject resultsRequestSOAP = null;
                try {
                    //不为空就报类型转换错误""
                    transport.call("", envelope);

                    if (envelope.getResponse() != null) {
                        resultsRequestSOAP = (SoapObject) envelope.bodyIn;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {

                    webServiceCallBack.callBack(resultsRequestSOAP);
                }

            }
        };
        new Thread(runable).start();

    }


    public static void login(String username, String password, WebServiceCallBack webServiceCallBack) {
        String endPoint;
        endPoint = loginPoint;
        String methodName = "loginForMobile";

        String encodeUserName = username;
        String encodePassword = password;
        if (TextUtils.isEmpty(encodePassword) || TextUtils.isEmpty(encodeUserName)) {
            return;
        }
        HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("userName", encodeUserName);
        properties.put("pwd", encodePassword);
        webservicesHelp(methodName, nameSpace, endPoint, properties, webServiceCallBack);
    }


    //添加soap的V-header
    private static Element[] getHeader(String nameSpace) {
        Element[] header = new Element[1];// 服务器过滤请求添加header
        header[0] = new Element().createElement(nameSpace, "SoapHeader");
        Element element1 = new Element().createElement(nameSpace, "appMercharCode");
        element1.addChild(Node.TEXT, "qerqwer");
        header[0].addChild(Node.ELEMENT, element1);
        Element element2 = new Element().createElement(nameSpace, "key");
        element2.addChild(Node.TEXT, "erqeqerqggwttytrrrrrr");
        header[0].addChild(Node.ELEMENT, element2);
        return header;
    }

}
