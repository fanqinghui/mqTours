package com.fqh.mq.rocketMq.fifo;

import com.fqh.mq.rocketMq.Constants;
import java.util.List;
import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @author fqh
 * @Description: 顺序消息读取
 * @date 2020/8/23下午11:52
 */
public class FIFOCustomer {

  public static void main(String[] args) throws MQClientException {
    DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("g1");
    consumer.setNamesrvAddr(Constants.NameServerAddr);
    consumer.subscribe("fifoTopic","*");
    consumer.setConsumeThreadMax(5);//开启最大线程数
    consumer.setConsumeThreadMin(3);//开启最小消费线程数

    consumer.registerMessageListener(new MessageListenerConcurrently() {
      @Override
      public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
          ConsumeConcurrentlyContext context) {
        for (MessageExt messageExt:msgs){
          System.out.println(new String(messageExt.getBody())+" Thread:"+Thread.currentThread().getId()+"queueID:"+messageExt.getQueueId());
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
      }
    });
    consumer.start();
  }
}
