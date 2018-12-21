package com.example.autoapi.service.httpImp;

import com.example.autoapi.service.HttpBuild;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
//unuse//
public class HttpBuildI implements HttpBuild{

    public static  CloseableHttpClient httpclient = null;
    public static HttpPost httpPost;
    public CloseableHttpResponse response =null;
    public void  HttpBuild() {
        httpclient = HttpClientBuilder.create().build();
    }


    public CloseableHttpResponse post(String url) {
        HttpBuild();
        try {
            response = httpclient.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

}
