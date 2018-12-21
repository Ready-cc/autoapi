package com.example.autoapi.service.httpImp;

import com.alibaba.fastjson.JSONObject;
import com.example.autoapi.Domain.Result;
import com.example.autoapi.service.HttpBuild;
import com.example.autoapi.service.HttpRequest;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;


@Service("HttpRequestI")
public class HttpRequestI implements HttpRequest,HttpBuild{

    public StringEntity stringEntity;
    public UrlEncodedFormEntity urlEncodedFormEntity;
    public static  CloseableHttpClient httpclient = null;
    static HttpPost httpPost;
    static HttpEntity httpEntity;
    public CloseableHttpResponse response =null;
    Result result;
    static SimpleDateFormat sdf;
    private long beginTime;
    private long endTime;

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    private static   String getTime(){
        sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        return sdf.format(new Date());
    }
    private static   long getTimeC(){
        return new Date().getTime();
    }
    @Override
    public void HttpBuild() {
        httpclient = HttpClientBuilder.create().build();
    }

    @Override
    public void httpClose() {
        try {
            httpclient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Result post(HttpPost httpPost) {
        HttpBuild();
        result=new Result();
        try {
            response= httpclient.execute(httpPost);
            if(response.getEntity()!=null){
                // 设置结果 code和实体
                result.setEntity(EntityUtils.toString(response.getEntity()));
                result.setCode(response.getStatusLine().getStatusCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Result post(String url) {
        HttpBuild();
        result=new Result();
        httpPost = new HttpPost(url);
        try {
            logger.info("====开始执行http请求======");
            beginTime=getTimeC();
            response= httpclient.execute(httpPost);
            endTime=getTimeC();
            logger.info("====请求结束======");
            logger.info("=url="+url);
            logger.info("====请求耗时======" +(endTime-beginTime));
            if(response.getEntity()!=null){
                // 设置结果 code和实体
                result.setEntity(EntityUtils.toString(response.getEntity()));
                result.setCode(response.getStatusLine().getStatusCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Result post(String url, String param) throws IOException {
         result=new Result();
        HttpBuild();
        httpPost = new HttpPost(url);
        httpPost.setHeader("Accept","application/json");
        httpPost.addHeader("Content-Type","application/json");
        //stringEntity = new StringEntity(param,"UTF-8");  十六进制
        stringEntity = new StringEntity(param, Charset.forName("utf8"));
        try {
            httpPost.setEntity(stringEntity);
            response= httpclient.execute(httpPost);
            if(response.getEntity()!=null){
                // 设置结果 code和实体
                result.setEntity(EntityUtils.toString(response.getEntity()).trim());
                result.setCode(response.getStatusLine().getStatusCode());
            }
        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(response!=null){
                response.close();
            }
            httpclient.close();
        }
        return result;
    }


    @Override
    public Result postJson(String url, JSONObject param) throws IOException {
        Result result=new Result();

        HttpBuild();
        httpPost = new HttpPost(url);
        httpPost.setHeader("Accept","application/json");
        httpPost.addHeader("Content-Type","application/json;charset=UTF-8");
        //stringEntity = new StringEntity(param,"UTF-8");  十六进制

        stringEntity = new StringEntity(param.toString());

//        stringEntity.setContentType("text/json");
//        stringEntity.setContentEncoding(new BasicHeader("Content-Type", "application/json,utf-8"));
        try {
            httpPost.setEntity(stringEntity);
            response= httpclient.execute(httpPost);
            if(response.getEntity()!=null){
                // 设置结果 code和实体
                result.setEntity(EntityUtils.toString(response.getEntity()));
                result.setCode(response.getStatusLine().getStatusCode());
            }
        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(response!=null){
                response.close();
            }
            httpclient.close();
        }
        return result;
    }

    public void close(){

    }
}
