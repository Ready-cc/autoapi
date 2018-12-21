package com.example.autoapi.controller;

import com.example.autoapi.test.runXml.runOne;
import org.jboss.logging.FormatWith;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class FileUpLoad {


    @RequestMapping("/file")
    public String file(){
        return "upload/fileupload";
    }

    @RequestMapping("upload")
    public String upload(@RequestParam("file") MultipartFile file){
        String uploadpath = "/code/autoapi/src/main/resources/templates/upload/";
        File dir = new File(uploadpath);
        if(!dir.exists()){
            dir.mkdir();
        }
        String filename = file.getOriginalFilename();
        File serverFile = new File(uploadpath+filename);
        if(!file.isEmpty()){
            try{
               file.transferTo(serverFile);
            } catch (IOException e) {
                e.printStackTrace();
                return "false";
            }
        }else{
            return "文件不能为空";
        }
        System.out.println("上传成功");
        return "true";
    }
    @RequestMapping("do")
    public String  doTest(){
        runOne runOne = new runOne();
        runOne.testxml();
        return "upload/fileupload";
    }
}
