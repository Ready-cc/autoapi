package com.example.autoapi.test.pamaAccountApi;

import com.example.autoapi.Domain.ConfigBean;
import com.example.autoapi.Domain.Result;
import com.example.autoapi.service.HttpRequest;
import com.example.autoapi.util.YmalRead;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@SpringBootTest
public class accountsApiTest extends AbstractTestNGSpringContextTests {

    @Resource(name = "HttpRequestI")
    HttpRequest httpRequest;
    @Resource
    YmalRead ymalRead;
    @Resource
    ConfigBean configBean;
    String url;
    String body;
    JsonObject jsonObject;
    List<Map> mapList;
    Gson gson;
    Result result;
    @BeforeClass
    public void init(){
        mapList = ymalRead.readAll("data/testObject.yml");
        gson= new Gson();
//        jsonObject = new JsonObject();
//        jsonObject.addProperty("custName","之家老用户");
//        jsonObject.addProperty("idNumber","340321197402226386");
    }

    @Test
    public void testOne() throws IOException {
        // 发起post请求
        result = httpRequest.post(
                configBean.getPamaUrl()+ mapList.get(0).get("url").toString(),
                gson.toJson(mapList.get(0).get("body"))
        );
        //int stauts =httpRequestI.post(url,body).getCode();
        System.out.println(result.getEntity().toString());
        Assert.assertEquals(result.getCode(),200);
    }
    @Test
    public void testTwo() throws IOException {
        result = httpRequest.post(
                configBean.getPamaUrl()+ mapList.get(1).get("url").toString(),
                gson.toJson(mapList.get(1).get("body"))
        );
        //int stauts =httpRequestI.post(url,body).getCode();
        System.out.println(result.getEntity().toString());
        Assert.assertEquals(result.getCode(),200);

    }
}
