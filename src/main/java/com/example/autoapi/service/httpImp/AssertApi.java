package com.example.autoapi.service.httpImp;

import com.example.autoapi.Domain.ExcelSettleCase;
import com.example.autoapi.Domain.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;



public class AssertApi {

    private static Logger logger = LoggerFactory.getLogger(AssertApi.class);
    public static boolean flag = true;


    public static void assertContain( Result result,ExcelSettleCase excelSettleCase) {

        switch (excelSettleCase.getCheck()){
            case "code":
                logger.info("============验证开始======");
               logger.info("实际结果:"+result.getCode());
               logger.info("预期结果:"+Integer.parseInt(excelSettleCase.getExpect()));
               try{
                   Assert.assertEquals(result.getCode(),Integer.parseInt(excelSettleCase.getExpect()));
               }catch (Error e){
                   logger.error(e.getMessage());
                   flag = false;
               }
                logger.info("============验证结束======");
                break;
            case "contains":
                logger.info("============验证开始======");
                logger.info("contains："+result.getEntity());
                try{
                    Assert.assertTrue(result.getEntity().contains(excelSettleCase.getExpect()));
                }catch (Error e){
                    logger.error(e.getMessage());
                    flag = false;
                }
                logger.info("============验证结束======");
                break;
             default:
                 logger.info("============无可校验元素======");
        }
    }



    public static void assertContain( Result result,String check,String expecet) {

        switch (check){
            case "code":
                logger.info("============验证开始======");
                logger.info("实际结果:"+result.getCode());
                logger.info("预期结果:"+Integer.parseInt(expecet));
                try{
                    Assert.assertEquals(result.getCode(),Integer.parseInt(expecet));
                }catch (Error e){
                    logger.error(e.getMessage());
                    flag = false;
                }
                logger.info("============验证结束======");
                break;
            case "contains":
                logger.info("============验证开始======");
                logger.info("contains："+result.getEntity());
                try{
                    Assert.assertTrue(result.getEntity().contains(expecet));
                }catch (Error e){
                    logger.error(e.getMessage());
                    flag = false;
                }
                logger.info("============验证结束======");
                break;
            default:
                logger.info("============无可校验元素======");
        }
    }
}
