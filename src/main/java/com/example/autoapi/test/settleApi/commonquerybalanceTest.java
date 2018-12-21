package com.example.autoapi.test.settleApi;

import com.example.autoapi.Domain.ConfigBean;
import com.example.autoapi.Domain.ExcelSettleCase;
import com.example.autoapi.Domain.Result;
import com.example.autoapi.service.ExcelToCase;
import com.example.autoapi.service.HttpRequest;
import com.example.autoapi.service.ParamsBuild;
import com.example.autoapi.service.httpImp.AssertApi;
import com.example.autoapi.util.ReadExcel;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;


@SpringBootTest
public class commonquerybalanceTest extends AbstractTestNGSpringContextTests {
    @Resource(name = "HttpRequestI")
    HttpRequest httpRequest;
    @Resource
    ExcelToCase excelToCase;
    @Resource
    ParamsBuild paramsBuild;
    @Resource
    ConfigBean configBean;
    ExcelSettleCase excelSettleCase;
    ReadExcel readExcel;
    Result result;
    String url;
    String body;
    List<ExcelSettleCase> list;

    @BeforeClass
    public void init() {
        try {
            readExcel = new ReadExcel("/src/main/resources/zzhtestdata.xlsx");
            readExcel.ReadSheet(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void tesTwo() throws IOException {
        excelSettleCase = excelToCase.SettleApiCase(readExcel, 2);
        // 发起post请求
        url = configBean.getSettleapiUrl() + excelSettleCase.getUrlpath();
        body = paramsBuild.settleApiParams(excelSettleCase);
        result = httpRequest.post(url + body);
//        AssertApi.assertContain(result,excelSettleCase);
    }


    @Test
    public void tesTwo2() throws IOException {
        excelSettleCase = excelToCase.SettleApiCase(readExcel, 4);
        // 发起post请求
        url = configBean.getSettleapiUrl() + excelSettleCase.getUrlpath();
        body = paramsBuild.settleApiParams(excelSettleCase);
        result = httpRequest.post(url + body);
        System.out.println(result.getEntity().toString());
//        Assert.assertEquals(result.getCode(), "200");
        Assert.assertTrue(result.getEntity().contains("total_asset"));
    }

    @Test
    public void testOne() throws IOException {
        System.out.println("=====================" + this.getClass().getName());
        excelSettleCase = excelToCase.SettleApiCase(readExcel, 2);
        // 发起post请求
        url = configBean.getSettleapiUrl() + excelSettleCase.getUrlpath();
        body = paramsBuild.settleApiParams(excelSettleCase);
        result = httpRequest.post(url + body);
        System.out.println(result.getEntity().toString());
        Assert.assertEquals(result.getCode(), 200);
        Assert.assertTrue(result.getEntity().contains("amount"));
    }

   @AfterClass
    public void close(){
        httpRequest.httpClose();
        logger.info("关闭请求链接");

    }


}
