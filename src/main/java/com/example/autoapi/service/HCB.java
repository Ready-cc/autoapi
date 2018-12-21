package com.example.autoapi.service;

import org.apache.http.HttpHost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;

public class HCB extends HttpClientBuilder {


    private HCB(){}

    public static HCB custom(){
        return new HCB();
    }

    /**
     * 设置代理
     * @param hostOrIp
     * @param port
     * @return
     */

    public HCB proxy(String hostOrIp,int port){
        HttpHost proxy = new HttpHost(hostOrIp,port,"service");
        DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
        return (HCB)this.setRoutePlanner(routePlanner);

    }
}
