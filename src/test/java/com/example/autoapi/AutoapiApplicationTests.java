package com.example.autoapi;

import com.example.autoapi.Domain.ConfigBean;
import com.example.autoapi.Domain.ExcelSettleCase;
import com.example.autoapi.service.ExcelToCase;
import com.example.autoapi.service.ParamsBuild;
import com.example.autoapi.service.httpImp.Excel2CaseI;
import com.example.autoapi.test.runXml.runOne;
import com.example.autoapi.util.ReadExcel;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.google.gson.Gson;
import com.example.autoapi.util.YmalRead;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AutoapiApplicationTests {
	YmalRead ymalRead;
	static ReadExcel readExcel;
	ExcelSettleCase excelSettleCase;

	@Resource
	ConfigBean configBean;
	@Resource
	ParamsBuild paramsBuild;
	@Resource
	ExcelToCase excelToCase;

	runOne runOne;
	//@BeforeClass
	public void init() throws IOException {
//		readExcel = new ReadExcel("/src/main/resources/zzhtestdata.xlsx");
//		readExcel.ReadSheet(1);
//		excelSettleCase = excelToCase.SettleApiCase(readExcel, 3);
	}
	@Test
	public void contextLoads() {
		ymalRead =  new YmalRead();
		List<Map> list;
		list = ymalRead.readAll("data/testObject.yml");
		System.out.println(list.get(0).get("url"));
		System.out.println(list.get(1).get("url"));
		System.out.println(list.get(0).get("body"));
	}
	@Test
	public void contextLoads1() {
		ymalRead =  new YmalRead();
		Gson gson = new Gson();
		List<Map> list;
		list = ymalRead.readAll("data/testObject.yml");
		System.out.println(list.get(0).get("url"));
		System.out.println(list.get(1).get("url"));
		System.out.println(gson.toJson(list.get(0).get("body")));
	}
	@Test
	public  void testconfig(){
		System.out.println(configBean.getPamaUrl());
	}


	@Test
	public  void testReadCell() throws IOException {
		readExcel = new ReadExcel();
		System.out.println(readExcel.getXssfCell(1));
//		readExcel.ReadSheet(0)
	}

	@Test
	public  void testReadCellbyRow() throws IOException {
		readExcel = new ReadExcel("/src/main/resources/zzhtestdata.xlsx");
		readExcel.ReadSheet(1);
		System.out.println(readExcel.getXssfCellByRow(2));
	}

	@Test
	public void testtoCase() throws IOException {
		readExcel = new ReadExcel("/src/main/resources/zzhtestdata.xlsx");
		readExcel.ReadSheet(1);
		Excel2CaseI excel2CaseI = new Excel2CaseI();
		System.out.println("canshu:"+excel2CaseI.SettleApiCase(readExcel,2).getParameters());
		System.out.println("key:"+excel2CaseI.SettleApiCase(readExcel,2).getKey());
		System.out.println("get_timestamp:"+excel2CaseI.SettleApiCase(readExcel,2).get_timestamp());
	}
	@Test
	public void testsettleApiParams() throws IOException {
//		readExcel = new ReadExcel("/src/main/resources/zzhtestdata.xlsx");
//		readExcel.ReadSheet(1);
		System.out.println(paramsBuild.settleApiParams(readExcel,2));
	}

	@Test
	public void testIp(){

	}
	@Test
	public  void testxml(){
		runOne =new runOne();
		runOne.test();
	}

}
