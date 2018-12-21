package com.example.autoapi.service;


import com.example.autoapi.Domain.ExcelSettleCase;
import com.example.autoapi.util.ReadExcel;

public interface ParamsBuild {

    public void settleApiParams();

    String settleApiParams(ReadExcel readExcel, int rowNo);

    String settleApiParams(ExcelSettleCase excelSettleCase);
}
