package com.example.autoapi.util;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    static String[] before;
    static String[] after;

    /**
     * partner_id >1,member_id >2,格式返回map
     *
     * @param value
     * @return
     */
    public static Map mainParams(String value) {
        Map<String, String> mainParam = new HashMap<>();
        before = value.trim().split("\\,");
        for (int i = 0; i < before.length; i++) {
            after = before[i].split("\\>");
            if(after.length==2){
                mainParam.put(dealString(after[0]), dealString(after[1]));
            }else{
                mainParam.put(dealString(after[0]),"");
            }
//            System.out.println(mainParam);
        }
        return mainParam;
    }

    /**
     * 返回拼接字符串 无连接符 settleapi md5用
     *
     * @param value
     * @return
     */

    public String mainParam(String value) {
        before = value.trim().split("\\,");
        StringBuilder aa = new StringBuilder();
        for (int i = 0; i < before.length; i++) {
            after = before[i].split("\\>");
            aa.append(StringUtils.join(after));
            System.out.println(aa);
        }
        return aa.toString().replace(" ", "");
    }

    public static  String dealString(String string) {
        Pattern p = Pattern.compile("\\s*|\t|\r|\n");
        /*\n 回车(\u000a)
             \t 水平制表符(\u0009)
             \s 空格(\u0008)
             \r 换行(\u000d)*/
        Matcher m = p.matcher(string);
        return m.replaceAll("");
    }

    public static  String settleParams(TreeMap<String,String> map){
        StringBuilder SrcSb = new StringBuilder();
        for(Map.Entry<String,String> entry : map.entrySet()){
            SrcSb.append(entry.getKey()).append("=");
            if(StringUtils.isNotBlank(entry.getValue())){
                SrcSb.append(entry.getValue());
            }
            SrcSb.append("&");

        }
        return StringUtils.substring(SrcSb.toString(),0,SrcSb.length()-1);
    }

}
