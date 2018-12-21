package com.example.autoapi.test;

import com.example.autoapi.service.Httptest;
import com.example.autoapi.util.ReadExcel;
import com.example.autoapi.util.WriteExcel;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * pama 账户相关类接口
 */

public  class PamaAcct {
    static  Httptest httptest;
    static   ReadExcel readExcel;
    static  WriteExcel wt;
    SimpleDateFormat date;
    String body;
   public static  void  pamaacct(){
        httptest = new Httptest();
        readExcel = new ReadExcel();
        wt = new WriteExcel();

    }

    public String getBody(int i) throws IOException {
        ReadExcel rd = new ReadExcel();
        rd.ReadExcel(0);
        body = rd.getXssfCell(0).get(i).toString();
       return  body;
    }

//    static Httptest httptest;
    public void kaihu() throws IOException {
         date = new SimpleDateFormat("yyyymmddhhmmss");
        String requesno = date.format(new Date());
        String uid =  "201801"+date.format(new Date());
        String ucid ="201802"+date.format(new Date());
        /**
         *
         */
        String body;
        this.pamaacct();
        readExcel.ReadExcel(0);
        String url = readExcel.getXssfCell(1,0)+"requestNo="+requesno+"&uid="+uid+"&ucid="+ucid;
        body = readExcel.getXssfCell(1,1);
        System.out.println(url);
        System.out.println(body);
//		httptest.doGet("http://testpay.autohome.com.cn/pama/accounts/1080000114580886/cards?requestNo=201704133001&uid=20170413001&ucid=20170413001");
        httptest.postAwrit(url,body);


    }

    /**
     *
     * 读取excel调用接口
     * 结果写入excel
     * @throws IOException
     */

    public  void isKaihuList() throws IOException {
        date = new SimpleDateFormat("yyyymmddhhmmss");
        String requesno = date.format(new Date());
        String uid =  "201801"+date.format(new Date());
        String ucid ="201802"+date.format(new Date());
        this.pamaacct();
        readExcel.ReadExcel(0);
        String url = readExcel.getXssfCell(1,0)+"requestNo="+requesno+"&uid="+uid+"&ucid="+ucid;
        for(int i=0;i<=readExcel.rowNum(0);i++){
            body =  readExcel.getXssfCell(0).get(i).toString();
            httptest.postAwrits(url,body,i);
        }

    }

}
