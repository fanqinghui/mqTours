package com.fqh.mq.rocketmq.mqWeb;

import com.fqh.mq.rocketmq.domain.User;
import java.io.IOException;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fqh
 * @Description: 发送Mq消息
 * @date 2020/8/27下午3:13
 */
@RestController
public class SendMsgController {

  @Autowired
  RocketMQTemplate rocketMQTemplate;

  @GetMapping("stringMsg")
  public Object sendStringMsg() {
    String body="i'm a springboot rocketMq string msg";
    return rocketMQTemplate.syncSend("stringTopic",body);
  }
  @GetMapping("userMsg")
  public void sendObjMsg() throws IOException {
    User user=new User(1L,"lxl",21);
    rocketMQTemplate.asyncSend("userTopic", user, new SendCallback() {
      @Override
      public void onSuccess(SendResult sendResult) {
        System.out.println("发送ok:"+sendResult.toString());
      }

      @Override
      public void onException(Throwable e) {
        System.out.println("发送失败:"+e.getMessage());
      }
    },3000);
    System.in.read();
  }
}
