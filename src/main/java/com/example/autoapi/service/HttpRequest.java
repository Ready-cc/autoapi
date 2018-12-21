package com.example.autoapi.service;

import com.example.autoapi.Domain.Result;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;


public interface HttpRequest {
    public  void httpClose();

    Result post(String url);


    public Result post(HttpPost httpPost);


    public Result post(String url, String param) throws IOException;

    Result postJson(String url, JSONObject param) throws IOException;

}
