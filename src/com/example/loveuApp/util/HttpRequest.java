package com.example.loveuApp.util;

/**
 * Created by dy on 2016/7/26.
 */
import android.content.Context;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;



/**
 * @author HUPENG
 * 网络访问辅助类
 * @version 1.0.1
 */
public class HttpRequest {
    /**
     * 服务器地,端口号与应用名组成的基本的URL
     * */
    private static final String BASE_URL = "";
    private static AsyncHttpClient client = new AsyncHttpClient();

    /**
     * 发送get请求的方法<br/>
     * @param context 上下文
     * @param url 部分url地址
     * @param params 封装了请求参数的类
     * @param responseHandler 回调接口
     * */
    public static void get(Context context,String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(context,getAbsoluteUrl(url), params, responseHandler);
    }

    /**
     * 发送post请求的方法<br/>
     * @param context 上下文
     * @param url 部分url地址
     * @param params 封装了请求参数的类
     * @param responseHandler 回调接口
     * */
    public static void post(Context context,String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(context,getAbsoluteUrl(url), params, responseHandler);
    }

    /**
     * 由基本的url+部分url 所组成的完整的url地址</br>
     * @param relativeUrl 部分url地址
     * @return 组成的完整的请求用的url地址
     * */
    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }


}
