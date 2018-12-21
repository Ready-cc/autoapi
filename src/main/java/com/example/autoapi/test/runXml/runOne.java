package com.example.autoapi.test.runXml;


import com.example.autoapi.test.testAll;
import org.testng.TestNG;

import java.util.ArrayList;
import java.util.List;


public class runOne {

    TestNG testNG;
    public  void test(){
        testNG = new TestNG();
        testNG.setTestClasses(new Class[]{testAll.class});
        testNG.run();
    }

    public void testxml(){
        testNG = new TestNG();
        List<String> suites = new ArrayList<>();
        suites.add(System.getProperty("user.dir")+"/testng.xml");
        testNG.setTestSuites(suites);
        testNG.run();
    }
}
