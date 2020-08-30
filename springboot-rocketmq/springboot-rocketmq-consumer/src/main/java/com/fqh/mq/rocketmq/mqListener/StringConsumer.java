package com.fqh.mq.rocketmq.mqListener;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @author fqh
 * @Description: 字符串消息发送
 * @date 2020/8/27下午2:49
 */
@Service
@RocketMQMessageListener(nameServer = "${rocketmq.name-server}",topic = "stringTopic", consumerGroup = "string_consumer")
public class StringConsumer implements RocketMQListener<String> {

  @Override
  public void onMessage(String s) {
    System.out.println("String Message is:"+s);
  }
}
