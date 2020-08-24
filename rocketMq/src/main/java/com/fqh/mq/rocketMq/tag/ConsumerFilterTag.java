package com.fqh.mq.rocketMq.tag;

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
 *
 * TOOD:注意，同一个consumerGroup组内不能同时订阅subscribe同一个topic的不同tag
 * 必须用不用的consumerGroup来定义同一个topic内的不同tag
 */
public class ConsumerFilterTag {

  public static void main(String[] args) throws MQClientException {
    DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("xxoo_taga");
    consumer.setNamesrvAddr(Constants.NameServerAddr);
    System.out.println("Tag_A");
    consumer.subscribe("topicTag","tag-a");
    //consumer.setMessageModel(MessageModel.BROADCASTING);//广播消息，所有相同组，定于topic的消费端都能收到消息
    consumer.setMessageModel(MessageModel.CLUSTERING);//集群消息--默认（相同组内的topic，集群消息只有一端会接收到）
    consumer.registerMessageListener(new MessageListenerConcurrently(){
      @Override
      public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list,
          ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        for (MessageExt messageExt:list){
          System.out.println(new String(messageExt.getBody()));
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
      }
    });
    consumer.start();
  }

}
