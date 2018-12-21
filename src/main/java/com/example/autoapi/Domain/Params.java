package com.example.autoapi.Domain;

import com.alibaba.fastjson.JSON;

public class Params {
    String data;
    JSON body;

    public String getParams() {
        return data;
    }

    public void setParams(String params) {
        this.data = params;
    }

    public JSON getBody() {
        return body;
    }

    public void setBody(JSON body) {
        this.body = body;
    }
}
