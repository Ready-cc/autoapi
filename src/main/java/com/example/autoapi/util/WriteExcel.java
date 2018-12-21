package com.example.autoapi.util;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

/**
 * 写入excel
 *
 *
 */
public class WriteExcel {
       XSSFSheet xssfSheet;
      XSSFRow xssfRow;
     XSSFCell xssfCell;
    XSSFWorkbook xssfWorkbook;
    String pathname  =System.getProperty("user.dir")+"/src/main/resources/zzhtestdata.xlsx";;

    public void  writeXssfCell(String aa) throws IOException {
        File file = new File(pathname);
        InputStream inputStream = new FileInputStream(file);
        xssfWorkbook = new XSSFWorkbook(inputStream);
        xssfSheet = xssfWorkbook.getSheetAt(0);
        xssfRow = xssfSheet.getRow(1);
        xssfCell =xssfRow.createCell(2);
        xssfCell.setCellType(xssfCell.CELL_TYPE_STRING);
        xssfCell.setCellValue(aa);
        FileOutputStream fos = new FileOutputStream(new File(pathname));
        xssfWorkbook.write(fos);
        fos.close();
    }

    public void  writeXssfCells(String result,int i) throws IOException {
        File file = new File(pathname);
        InputStream inputStream = new FileInputStream(file);
        xssfWorkbook = new XSSFWorkbook(inputStream);
        xssfSheet = xssfWorkbook.getSheetAt(0);
        xssfRow = xssfSheet.getRow(i);
        xssfCell =xssfRow.createCell(2);
        xssfCell.setCellType(xssfCell.CELL_TYPE_STRING);
        xssfCell.setCellValue(result);
        FileOutputStream fos = new FileOutputStream(new File(pathname));
        xssfWorkbook.write(fos);
        fos.close();
    }


}
