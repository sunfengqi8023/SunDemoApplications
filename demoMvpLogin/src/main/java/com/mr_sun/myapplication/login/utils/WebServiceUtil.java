package com.mr_sun.myapplication.login.utils;

import android.text.TextUtils;

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
    public static String loginPoint = "http://dev.boyuyun.com.cn:9018/" + "/services/loginService";

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
        final HttpTransportSE transport = new HttpTransportSE(endPoint, 15000);

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


}
