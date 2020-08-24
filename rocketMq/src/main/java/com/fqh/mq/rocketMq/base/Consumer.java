package com.fqh.mq.rocketMq.base;

import com.fqh.mq.rocketMq.Constants;
import java.util.List;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

/**
 * @author fqh
 * @Description:
 * @date 2020/8/22下午11:47
 */
public class Consumer {

  public static void main(String[] args) throws MQClientException {
    DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumerGroup");
    consumer.setNamesrvAddr(Constants.NameServerAddr);
    consumer.subscribe("topic01","*");
    consumer.setMessageModel(MessageModel.BROADCASTING);//广播消息，所有相同组，定于topic的消费端都能收到消息
    //consumer.setMessageModel(MessageModel.CLUSTERING);//集群消息--默认（相同组内的topic，集群消息只有一端会接收到）
    consumer.registerMessageListener(new MessageListenerConcurrently(){
      @Override
      public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list,
          ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        for (MessageExt messageExt:list){
          System.out.println(new java.lang.String(messageExt.getBody()));
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
      }
    });
    consumer.start();
  }

}
