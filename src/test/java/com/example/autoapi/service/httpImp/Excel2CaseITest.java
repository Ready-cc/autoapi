package com.example.autoapi.service.httpImp;

import com.example.autoapi.Domain.ConfigBean;
import com.example.autoapi.Domain.ExcelSettleCase;
import com.example.autoapi.service.ExcelToCase;
import com.example.autoapi.service.ParamsBuild;
import com.example.autoapi.util.ReadExcel;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Excel2CaseITest extends AbstractTestNGSpringContextTests {

     ReadExcel readExcel;
    @Resource
    ExcelToCase excelToCase;


    @Test
    public void testSettleApiCaseO() throws Exception {
        readExcel = new ReadExcel("/src/main/resources/zzhtestdata.xlsx");
        readExcel.ReadSheet(1);
        Object[][] objects = excelToCase.SettleApiCaseO(readExcel);
        for(int i = 0;i<objects.length;i++){
            System.out.println(objects[i].toString());
        }

    }
}