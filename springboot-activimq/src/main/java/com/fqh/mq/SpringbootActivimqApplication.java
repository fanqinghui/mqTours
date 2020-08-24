package com.fqh.mq;

import com.fqh.mq.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableJms
@RestController
public class SpringbootActivimqApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringbootActivimqApplication.class, args);
  }

  @Autowired
  SendService sendService;

  @RequestMapping("sendMsg")
  public void sendMsg(){
    sendService.send("user","hello");
  }

  @RequestMapping("sendObj")
  public void sendObj(){
    Girl girl=new Girl();
    girl.setAge(1);
    girl.setName("lxl");
    girl.setPrice(90.0f);
    sendService.sendObj("girl",girl);
  }

}
