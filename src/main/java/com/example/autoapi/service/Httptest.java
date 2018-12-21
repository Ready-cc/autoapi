package com.example.autoapi.service;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import com.example.autoapi.util.WriteExcel;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * http相关请求方法
 *
 *
 */
//unuse
public class Httptest {

   static CloseableHttpClient httpClient;
   static   HttpUriRequest httpUriRequest;
   static  HttpEntity entity;
   static   CloseableHttpResponse response;
   static HttpPost httpPost;
    static WriteExcel wt;

    public static void  httptest(){
       HCB.custom().build();
       httpClient = HttpClients.createDefault();

    }

    public  void doGet(String url) throws IOException {
       this.httptest();
//        HCB.custom().build();
//        CloseableHttpClient httpClient = HttpClients.createDefault();
         httpUriRequest = new HttpGet(url);
         response = httpClient.execute(httpUriRequest);
        entity = response.getEntity();
        if(entity!=null){
            String  entityStr= EntityUtils.toString(entity,"utf-8");
            System.out.println(entityStr);
        }
    }

    /**
     * post url 无参数
     * @param url
     * @throws IOException
     */
    public void doPost(String url) throws IOException {
       this.httptest();
        httpPost = new HttpPost(url);
       response = httpClient.execute(httpUriRequest);
       entity = response.getEntity();
        if(entity!=null){
            String  entityStr= EntityUtils.toString(entity,"utf-8");
            System.out.println(entityStr);
        }
    }

    /**
     * post 请求raw参数
     * @param url
     * @param body
     */
    public void doPost(String url,String body){
        this.httptest();
        httpPost = new HttpPost(url);
        try {
            httpPost.setEntity(new StringEntity(body));
            response = httpClient.execute(httpPost);
            entity = response.getEntity();
            System.out.println("==============返回结果打印开始======");
            System.out.println(EntityUtils.toString(entity,"GBK"));
            System.out.println("==============返回结果打印结束======");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            this.close();
        }

    }


    /**
     *
     *
     * 不显示返回结果
     * 结果需写入excel
     * @param url
     * @param
     */
    public void postAwrit(String url,String body){
        wt  = new WriteExcel();
        this.httptest();
        httpPost = new HttpPost(url);
        try {
            httpPost.setEntity(new StringEntity(body));
            response = httpClient.execute(httpPost);
            entity = response.getEntity();
//            System.out.println("==============返回结果打印开始======");
//            System.out.println(EntityUtils.toString(entity,"GBK"));
//            System.out.println("==============返回结果打印结束======");
            wt.writeXssfCell(EntityUtils.toString(entity,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            this.close();
        }

    }

    /**
     *
     * 执行excel
     * 不显示返回结果
     * 结果需写入excel
     * @param url
     * @param
     */
    public void postAwrits(String url,String body,int i){
        wt  = new WriteExcel();
        this.httptest();
        httpPost = new HttpPost(url);
        try {
            httpPost.setEntity(new StringEntity(body));
            response = httpClient.execute(httpPost);
            entity = response.getEntity();
//            System.out.println("==============返回结果打印开始======");
//            System.out.println(EntityUtils.toString(entity,"GBK"));
//            System.out.println("==============返回结果打印结束======");
            wt.writeXssfCells(EntityUtils.toString(entity,"UTF-8"),i);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            this.close();
        }

    }

    /**
     *
     * @param url
     * @param params
     */
    public  void doPost(String url,Map<String,String> params) {
        this.httptest();
        httpPost = new HttpPost(url);
        List<NameValuePair> ps = new ArrayList<>();
        for (String pkey : params.keySet()) {
            ps.add(new BasicNameValuePair(pkey, params.get(pkey)));
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(ps));
            response = httpClient.execute(httpPost);
            entity = response.getEntity();
            System.out.println(EntityUtils.toString(entity, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.close();

        }
    }

    public void getBody(){

    }

    public void close(){
        try {
            if(httpPost!=null){
                httpPost.releaseConnection();
            }
            if(httpClient!=null){
                httpClient.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
