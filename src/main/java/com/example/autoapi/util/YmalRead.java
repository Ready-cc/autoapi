package com.example.autoapi.util;

import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;

/**
 *
 * 未完成
 */
@Service
public class YmalRead {
    private  Yaml yaml;

    public void readAll() {
        yaml = new Yaml();
        Iterable<Object> ret = yaml.loadAll(this.getClass().getClassLoader().getResourceAsStream("data/testObject.yml"));
        Map<String, Object> caseMap = new HashMap<>();
        for (Object o : ret) {
            caseMap.put("case" + caseMap.size(), transBean2Map(o));
        }
        System.out.println(caseMap);

    }

    /**
     *
     * 读取yaml 作为测试数据
     * json数据需单独转
     * @param filepath
     * @return
     */
    public List<Map> readAll(String filepath){
        Yaml yaml = new Yaml();
        Iterable<Object> cases =yaml.loadAll(this.getClass().getClassLoader().getResourceAsStream(filepath));
        Map map;
        List<Map> listmap= new ArrayList<>();
        //读取ymal文件中
        for (Object o : cases){
            map = (HashMap) o;
            listmap.add(map);
        }
        return  listmap;
    }

    public static Map<String, Object> transBean2Map(Object obj) {

        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (key.equals("class")) {
                    continue;
                }
                // 得到property对应的getter方法
                Method getter = property.getReadMethod();
                Object value = getter.invoke(obj);
                map.put(key, value);
            }
        } catch (Exception e) {
            System.out.println("transBean2Map Error " + e);
        }
        return map;
    }
}
