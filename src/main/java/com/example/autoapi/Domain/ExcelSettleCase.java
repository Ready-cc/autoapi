package com.example.autoapi.Domain;

import com.example.autoapi.util.StringUtil;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class ExcelSettleCase {
    String id;
    String casename;
    String urlpath;
    String init;
    String key;
    Long _timestamp;
    TreeMap parameters;
    String check;
    String expect;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCasename() {
        return casename;
    }

    public void setCasename(String casename) {
        this.casename = casename;
    }

    public String getUrlpath() {
        return urlpath;
    }

    public void setUrlpath(String urlpath) {
        this.urlpath = urlpath;
    }

    public String getInit() {
        return init;
    }

    public void setInit(String init) {
        this.init = init;
    }

    public TreeMap getParameters() {

        return  parameters;
    }

    public void setParameters(TreeMap parameters) {
        this.parameters = parameters;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getExpect() {
        return expect;
    }

    public void setExpect(String expect) {
        this.expect = expect;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long get_timestamp() {
        _timestamp = new Date().getTime()/1000;
        return _timestamp;
    }
}
