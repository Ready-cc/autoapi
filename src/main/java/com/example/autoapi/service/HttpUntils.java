package com.example.autoapi.service;


import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class HttpUntils {

    private static final int SOCKET_TIMEOUT = 20000;
    private static final int CO_TIMEOUT = 20000;
    static Logger logger = LogManager.getLogger(HttpUntils.class);
    private  static  RequestConfig requestConfig;
    private  static CloseableHttpClient httpClient;

    private HttpUntils() {
    }

    private static void init(){
         requestConfig = RequestConfig.custom().setSocketTimeout(SOCKET_TIMEOUT).setConnectTimeout(CO_TIMEOUT).build();
        httpClient = HttpClientBuilder.create().build();
    }


    public  void doGet(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
       HttpUriRequest httpUriRequest = new HttpGet(url);
        HttpResponse response= httpClient.execute(httpUriRequest);
        HttpEntity entity = response.getEntity();
        if(entity!=null){
            String  entityStr= EntityUtils.toString(entity,"utf-8");
            System.out.println(entityStr);
        }
    }


    private static String getResponse(CloseableHttpClient httpClient, HttpPost httpPost) throws IOException {
        String responseStr;
        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            int status = response.getStatusLine().getStatusCode();
            if(status >=200 && status <= 300){
                HttpEntity entity = response.getEntity();
                responseStr = EntityUtils.toString(entity, "utf-8");
                EntityUtils.consume(entity);
            }else {
                logger.error("请求错误，状态码为："+response.getStatusLine().getStatusCode());
                throw new ClientProtocolException("意外的状态返回: " + status);
            }
            return responseStr;
        }
    }


    private static String getResponseJson(CloseableHttpClient httpClient,HttpPost httpPost) throws IOException {
        String responseJson;
        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            int status = response.getStatusLine().getStatusCode();
            if(status >=200 && status <= 300){
                HttpEntity entity = response.getEntity();
                responseJson = JSONObject.toJSONString(EntityUtils.toString(entity,"utf-8"));
                EntityUtils.consume(entity);
            }else {
                logger.error("请求错误，状态码为："+response.getStatusLine().getStatusCode());
                throw new ClientProtocolException("意外的状态返回: " + status);
            }
            return responseJson;
        }
    }

    public static String doPost(String url, String postData,String SoJ) throws IOException {
        init();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new ByteArrayEntity(postData.getBytes()));

        //设置超时时间
//        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(SOCKET_TIMEOUT).setConnectTimeout(CO_TIMEOUT).build();
//        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        httpPost.setConfig(requestConfig);

        if(SoJ=="str"){
            return getResponse(httpClient, httpPost);
        }else {
            return getResponseJson(httpClient,httpPost);
        }

    }

    /**
     * post Json格式请求
     * SoJ 返回格式 字符串orjson
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static String doPostJson(String url, String params,String SoJ) throws IOException {
        init();
        StringEntity stringEntity = new StringEntity(params);
        stringEntity.setContentEncoding("UTF-8");
        stringEntity.setContentType("text/json");

        //设置超时时间
//        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(SOCKET_TIMEOUT).setConnectTimeout(CO_TIMEOUT).build();
//        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url);
        try {
            httpPost.setEntity(stringEntity);
            httpPost.setConfig(requestConfig);

            if(SoJ=="str"){
                return getResponse(httpClient, httpPost);
            }else {
                return getResponseJson(httpClient,httpPost);
            }

        }
        finally {
            httpClient.close();
        }

    }

}

