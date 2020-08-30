package com.fqh.mq.rocketmq.mqWeb;

import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fqh
 * @Description: 发送事务消息
 * @date 2020/8/30上午10:09
 */
@RestController
public class SendTransMsgController {

  @Autowired
  RocketMQTemplate rocketMQTemplate;

  @GetMapping("sendTransMsg")
  public void sendTransMsg() throws InterruptedException {
    for(int i=0;i<10;i++){
      org.springframework.messaging.Message<String> stringMessage = MessageBuilder
          .withPayload("this is transMsg" + i)
          .setHeader(RocketMQHeaders.TRANSACTION_ID, "key:id:" + i)
          .build();
      rocketMQTemplate.sendMessageInTransaction("trans_topic",stringMessage,"key:id:"+i);
      Thread.sleep(10);
    }
  }


}
