package com.fqh.mq.rocketmq.mqListener;

import java.util.List;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListener;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author fqh
 * @Description: 事务消费者
 * @date 2020/8/30上午10:20
 */
@Service
@RocketMQMessageListener(topic="trans_topic",consumerGroup = "myTransConsumerGroup")
public class StringTransConsumer implements RocketMQListener<String> {

  @Override
  public void onMessage(String s) {
    System.out.println(s);
  }
}
