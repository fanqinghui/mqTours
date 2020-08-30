package com.fqh.mq.rocketmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class RocketMqConsumerApplication {

  public static void main(String[] args) {
    SpringApplication.run(RocketMqConsumerApplication.class, args);
  }
}
