package com.example.autoapi.Domain;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix="api.baseurl")
public class ConfigBean {

    String settleapi;
    String pama;

    public String getSettleapiUrl() {
        return settleapi;
    }

    public String getPamaUrl() {
        return pama;
    }


    public void setSettleapi(String settleapi) {
        this.settleapi = settleapi;
    }

    public void setPama(String pama) {
        this.pama = pama;
    }



}
