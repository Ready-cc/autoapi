package com.example.autoapi.test;

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
import org.testng.IAlterTestName;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.TreeMap;


@SpringBootTest
public class testAll extends AbstractTestNGSpringContextTests {
    @Resource(name = "HttpRequestI")
    HttpRequest httpRequest;
    @Resource
    ExcelToCase excelToCase;
    @Resource
    ConfigBean configBean;
    String newname;
    Object[][] objects;

    ReadExcel readExcel;
    Result result;
    String url;
    String body;
    List<ExcelSettleCase> list;
    String path = "/src/main/resources/";
    String getPath = "/src/main/resources/templates/upload/";

    @BeforeClass
    public void init() {
        try {
            readExcel = new ReadExcel(getPath+"zzhtestdata1.xlsx");
            readExcel.ReadSheet(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @DataProvider(name = "dp")
    public Object[][] createData() {
        try {
            objects = excelToCase.SettleApiCaseO(readExcel);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return objects;
    }


    @Test(dataProvider = "dp",description="all")
    public void test(String name,String url,String body,String check,String excepct) {
      logger.info(name+"\n==测试结果==");
        try {
            list = excelToCase.SettleApiCase(readExcel);
        } catch (IOException e) {
            e.printStackTrace();
        }
        result = httpRequest.post(configBean.getSettleapiUrl()+url + body);
                AssertApi.assertContain(result,check,"false");
    }


    @AfterClass
    public void close(){
        httpRequest.httpClose();
        logger.info("关闭请求链接");

    }
}
