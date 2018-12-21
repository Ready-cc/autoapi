package com.example.autoapi.service.httpImp;

import com.example.autoapi.Domain.ExcelSettleCase;
import com.example.autoapi.service.ExcelToCase;
import com.example.autoapi.service.ParamsBuild;
import com.example.autoapi.util.ReadExcel;
import com.example.autoapi.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service("Excel2CaseI")
public class Excel2CaseI implements ExcelToCase {


    ExcelSettleCase excelSettleCase;
    ReadExcel readExcel;
    TreeMap map;
    TreeMap paramsMap;
    List<TreeMap> listmap;
    List<ExcelSettleCase> listCase;
    @Resource
    ParamsBuild paramsBuild;
    Object[][] objects;

    public void Excel2CaseI(String path, int sheet) {
        try {
            readExcel = new ReadExcel(path, sheet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 需签名的所有参数，map格式
     *
     * @param
     * @param
     * @return
     * @throws IOException
     */
    public TreeMap paramsJoin(Map map) throws IOException {
//        map = readExcel.getXssfCellByRow(rowNo);
        paramsMap = new TreeMap();
        paramsMap.put("_appid", map.get("_appid"));
        paramsMap.put("sign_method", map.get("sign_method"));
        paramsMap.put("format", map.get("format"));
        paramsMap.putAll(StringUtil.mainParams(map.get("parameters").toString()));
        return paramsMap;
    }

    /**
     * 所有用例字段转换为ExcelSettleCase对象格式--row
     *
     * @param readExcel
     * @param rowNo
     * @return
     */
    @Override
    public ExcelSettleCase SettleApiCase(ReadExcel readExcel, int rowNo) {
        try {
            excelSettleCase = new ExcelSettleCase();
            map = readExcel.getXssfCellByRow(rowNo);
            excelSettleCase.setId(map.get("id").toString());
            excelSettleCase.setCasename(map.get("casename").toString());
            excelSettleCase.setUrlpath(map.get("urlpath").toString());
            excelSettleCase.setCheck(map.get("check").toString());
            excelSettleCase.setExpect(map.get("expect").toString());
            excelSettleCase.setKey(map.get("key").toString());
            excelSettleCase.setParameters(this.paramsJoin(map));
//            excelSettleCase.setParameters();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return excelSettleCase;
    }

    /**
     * 所有用例字段转换为ExcelSettleCase对象格式--sheet
     *
     * @param readExcel
     * @return
     */
    @Override
    public List<ExcelSettleCase> SettleApiCase(ReadExcel readExcel) throws IOException {
        listCase = new ArrayList<>();
        listmap = readExcel.getXssfCellAll();
        for (int i = 0; i < listmap.size(); i++) {
            excelSettleCase = new ExcelSettleCase();
            excelSettleCase.setId(listmap.get(i).get("id").toString());
            excelSettleCase.setCasename(listmap.get(i).get("casename").toString());
            excelSettleCase.setUrlpath(listmap.get(i).get("urlpath").toString());
            excelSettleCase.setCheck(listmap.get(i).get("check").toString());
            excelSettleCase.setExpect(listmap.get(i).get("expect").toString());
            excelSettleCase.setKey(listmap.get(i).get("key").toString());
            excelSettleCase.setParameters(this.paramsJoin(listmap.get(i)));
            listCase.add(i, excelSettleCase);
        }
        return listCase;
    }

    /**
     * 返回testng的dataprovid 数组格式  url body result
     * @param readExcel
     * @return
     * @throws IOException
     */
    @Override
    public Object[][] SettleApiCaseO(ReadExcel readExcel) throws IOException {
        listmap = readExcel.getXssfCellAll();
        listCase = this.SettleApiCase(readExcel);

        readExcel.ReadSheet(1);
        int n = readExcel.getXssfCellAll().size();
        objects = new Object[n][];
        for (int i = 0; i < n; i++) {
            objects[i] = new Object[]{
                    listCase.get(i).getCasename(),listCase.get(i).getUrlpath().toString(),paramsBuild.settleApiParams(listCase.get(i)),listCase.get(i).getCheck(),listCase.get(i).getExpect()

            };

            System.out.println(objects.toString());
        }
        return  objects;

    }
}
