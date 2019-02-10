package com.zhouhuan.my.shop.commons.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;

/**
 * HttpClient 工具类
 *
 * @Title:HttpClientUtils
 * @Author hz_zhouhuan
 * @Version 1.0.0
 * @Date 2019/2/9 16:25
 */
public class HttpClientUtils {

    public static final String GET = "get";
    public static final String POST = "post";

    public static final String REQUEST_HANDER_CONNECTION = "keep-alive";
    public static final String REQUEST_HANDER_USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:65.0) Gecko/20100101 Firefox/65.0";

    /**
     * Get 请求
     *
     * @param url 请求地址
     * @return
     */
    public static String doGet(String url) {
        return createResuest(url, GET, null);
    }

    /**
     * Get 请求
     *
     * @param url    请求地址
     * @param cookie cookie
     * @return
     */
    public static String doGet(String url, String cookie) {
        return createResuest(url, GET, cookie);
    }

    /**
     * POST 请求
     *
     * @param url    请求地址
     * @param params 请求参数（可选）
     * @return
     */
    public static String doPost(String url, BasicNameValuePair... params) {
        return createResuest(url, POST, null, params);
    }

    /**
     * POST 请求
     *
     * @param url    请求地址
     * @param cookie cookie
     * @param params 请求参数（可选）
     * @return
     */
    public static String doPost(String url, String cookie, BasicNameValuePair... params) {
        return createResuest(url, POST, cookie, params);
    }

    /**
     * 创建请求
     *
     * @param url           请求地址
     * @param requestMethod 请求方式 GET/POST
     * @param cookie        cookie
     * @param params        请求参数，仅限于 POST 请求使用
     * @return
     */
    private static String createResuest(String url, String requestMethod, String cookie, BasicNameValuePair... params) {
        // 创建 HttpClient 客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = null;

        try {
            //请求结果
            result = null;
            //请求方式
            HttpGet httpGet = null;
            HttpPost httpPost = null;
            //响应方式
            CloseableHttpResponse httpResponse = null;
            // GET 请求
            if (GET.equals(requestMethod)) {
                // 创建 HttpGet 请求
                httpGet = new HttpGet(url);
                // 设置长连接
                httpGet.setHeader("Connection", REQUEST_HANDER_CONNECTION);
                // 设置代理（模拟浏览器版本）
                httpGet.setHeader("User-Agent", REQUEST_HANDER_USER_AGENT);
                // 设置 Cookie
                httpGet.setHeader("Cookie", cookie);
                httpResponse = httpClient.execute(httpGet);
            }
            // POST 请求
            else if (POST.equals(requestMethod)) {
                httpPost = new HttpPost(url);
                httpPost.setHeader("Connection", REQUEST_HANDER_CONNECTION);
                httpPost.setHeader("User-Agent", REQUEST_HANDER_USER_AGENT);
                httpPost.setHeader("Cookie", cookie);
                //有参数进来
                if (params != null && params.length > 0) {
                    // 设置 HttpPost 参数
                    httpPost.setEntity(new UrlEncodedFormEntity(Arrays.asList(params), "UTF-8"));
                }
                httpResponse = httpClient.execute(httpPost);
            }
            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

}
