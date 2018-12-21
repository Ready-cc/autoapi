package com.example.autoapi.service;

import com.example.autoapi.Domain.ExcelSettleCase;
import com.example.autoapi.Domain.Result;
import com.example.autoapi.util.ReadExcel;

import java.io.IOException;
import java.util.List;

public interface ExcelToCase {

    ExcelSettleCase SettleApiCase(ReadExcel readExcel,int rowNo);

    List<ExcelSettleCase> SettleApiCase(ReadExcel readExcel) throws IOException;

    Object[][] SettleApiCaseO(ReadExcel readExcel) throws IOException;

}
