package com.example.autoapi;

import com.example.autoapi.Domain.ConfigBean;
import com.example.autoapi.test.runXml.runOne;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ConfigBean.class})
public class AutoapiApplication {
	public  static void main(String[] args){
        SpringApplication.run(AutoapiApplication.class,args);
	}
}