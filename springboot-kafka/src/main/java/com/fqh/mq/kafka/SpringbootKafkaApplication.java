package com.fqh.mq.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringbootKafkaApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringbootKafkaApplication.class, args);
  }
}
