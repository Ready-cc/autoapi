package com.example.autoapi.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CaseList {

    @RequestMapping("/index")
    public String index(){
        return "case/index";
    }

    public ModelAndView list(){
        ModelAndView list = new ModelAndView();

        return list;
    }
}



