package com.example.autoapi.service.httpImp;

import com.example.autoapi.Domain.ExcelSettleCase;
import com.example.autoapi.service.ExcelToCase;
import com.example.autoapi.service.ParamsBuild;
import com.example.autoapi.util.ReadExcel;
import com.example.autoapi.util.SignUtil;
import com.example.autoapi.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.TreeMap;

@Service("ParamsBuildI")
public class ParamsBuildI implements ParamsBuild {

    String beforSign;
    String _sign;
    TreeMap<String,String> map;
    String key;


    @Override
    public void settleApiParams() {

    }

    @Override
    public String settleApiParams(ReadExcel readExcel, int rowNo) {
        return null;
    }

    @Override
    public String settleApiParams(ExcelSettleCase excelSettleCase) {
//        excelSettleCase = excelToCase.SettleApiCase(readExcel,rowNo);
        map = excelSettleCase.getParameters();
        key = excelSettleCase.getKey();
        map.put("_timestamp", excelSettleCase.get_timestamp().toString());
        beforSign = StringUtil.dealString(SignUtil.buildSignSrcSettle(map));
        try {
            _sign = SignUtil.signSettle(beforSign,key,"utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("签名原串："+key+beforSign+key);
        System.out.println("签名："+_sign);
        map.put("_sign",_sign);
        return StringUtil.settleParams(map);

    }
}
