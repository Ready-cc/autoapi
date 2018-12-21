package com.example.autoapi.util;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * excel读取
 * url body reslut
 * /resources/zzhtestdata.xlsx
 */
public class ReadExcel {
    XSSFSheet xssfSheet;
    XSSFRow xssfRow;
    XSSFCell xssfCell;
    List<Map> listmap;
    List<TreeMap> listTreemap;
    TreeMap<String, String> map;
    InputStream inputStream;
    XSSFWorkbook xssfWorkbook;
    File file;
    ReadExcel readExcel;

    public ReadExcel() {
    }


    public ReadExcel(String path) throws IOException {
        file = new File(System.getProperty("user.dir") + path);
        inputStream = new FileInputStream(file);
        xssfWorkbook = new XSSFWorkbook(inputStream);
    }

    public ReadExcel(String path, int index) throws IOException {
        file = new File(System.getProperty("user.dir") + path);
        inputStream = new FileInputStream(file);
        xssfWorkbook = new XSSFWorkbook(inputStream);
        xssfSheet = xssfWorkbook.getSheetAt(index);
    }

    public static void ReadExcel(int index) throws IOException {
        File file = new File(System.getProperty("user.dir") + "/src/main/resources/zzhtestdata.xlsx");
        InputStream inputStream = new FileInputStream(file);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(index);
    }

    public void ReadSheet(int sheet) {
        xssfSheet = xssfWorkbook.getSheetAt(sheet);
    }
//
//    public XSSFSheet ReadExcel(int index, String path) throws IOException {
//        File file = new File(System.getProperty("user.dir") + path);
//        InputStream inputStream = new FileInputStream(file);
//        xssfWorkbook = new XSSFWorkbook(inputStream);
//        return xssfSheet = xssfWorkbook.getSheetAt(index);
//    }

    /**
     * 获取用例数 去除头部 -2
     *
     * @param index
     * @return
     * @throws IOException
     */
    public int rowNum(int index) throws IOException {
        this.ReadExcel(index);
        return xssfSheet.getPhysicalNumberOfRows();
    }

    public int rowNum() throws IOException {
        return xssfSheet.getPhysicalNumberOfRows();
    }
    /**！！！！
     * 获取列数
     * 从第3行 有用例数据的开始
     * 方法maybe 有问题
     * @param rowNo
     * @return
     * @throws IOException
     */
    public int cellNum(int rowNo) throws IOException {
//        this.ReadExcel(index);
        return xssfSheet.getRow(rowNo).getPhysicalNumberOfCells();
    }

    //用例从第3行开始，以第三行为例计算列数
    public int cellNum() throws IOException {
//        this.ReadExcel(index);
        return xssfSheet.getRow(2).getPhysicalNumberOfCells();
    }

    public String getXssfCell(int row, int cell) {
        xssfRow = xssfSheet.getRow(row);
        xssfCell = xssfRow.getCell(cell);
        return getValue(xssfCell);
    }

    private String getValue(XSSFCell xssfCell) {
        String result = "";
        xssfCell.setCellType(xssfCell.CELL_TYPE_STRING);
        result = xssfCell.getStringCellValue();
        return result;
    }

    /**
     * 取sheet内所有数据，并转为list<map>格式
     * int cellnum = this.cellNum(sheet); 方法有更改
     * @param sheet
     * @return
     * @throws IOException
     */
    public List getXssfCell(int sheet) throws IOException {
        int rownum = this.rowNum(sheet);
        int cellnum = this.cellNum();
//        map = new HashMap<>();
        listmap = new ArrayList();
        this.ReadExcel(sheet);
        for (int i = 2; i <= rownum - 1; i++) {
            map = new TreeMap<>();
            for (int j = 1; j <= cellnum - 3; j++) {
                map.put(this.getXssfCell(1, j), this.getXssfCell(i, j));
            }
            listmap.add(map);
        }
        return listmap;
    }


    /**1
     * 取所有数据1
     * @param sheet
     * @return
     * @throws IOException
     */
    public List getXssfCells(int sheet) throws IOException {
        listTreemap = new ArrayList();
        for (int i = 0; i <= this.rowNum(sheet); i++) {
            listTreemap.add(i, this.getXssfCellByRow(i));
        }
        return listTreemap;
    }

    /**
     * 取所有数据2
     * @param
     * @return
     * @throws IOException
     */
    public List<TreeMap> getXssfCellAll(){
        listTreemap = new ArrayList<>();
        try {
            int rowNum = this.rowNum();
            for(int i=2;i<rowNum;i++){
                listTreemap.add(this.getXssfCellByRow(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listTreemap;
    }

    /**
     * 取指定行的用例
     * @param rowNo
     * @return
     * @throws IOException
     */
    public TreeMap<String,String> getXssfCellByRow(int rowNo) throws IOException {
        int cellnum = this.cellNum(rowNo);
            map = new TreeMap<>();
            for (int j = 0; j < cellnum; j++) {
                map.put(this.getXssfCell(1, j), this.getXssfCell(rowNo,j));
            }
        return map;
    }



    public Map getXssfCellMap(int sheet) throws IOException {
        map = new TreeMap<>();
        for (int i = 0; i <= this.rowNum(sheet); i++) {
            for (int j = 0; j <= this.cellNum(sheet); j++) {
                map.clear();
                map.put(this.getXssfCell(1, 1), this.getXssfCell(i, j));
                map.put(this.getXssfCell(1, 2), this.getXssfCell(i, j));
                map.put(this.getXssfCell(1, 3), this.getXssfCell(i, j));
                map.put(this.getXssfCell(1, 4), this.getXssfCell(i, j));
                map.put(this.getXssfCell(1, 5), this.getXssfCell(i, j));
            }
        }
        return map;
    }

}
