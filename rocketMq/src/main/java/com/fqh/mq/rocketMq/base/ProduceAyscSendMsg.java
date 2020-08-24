package com.fqh.mq.rocketMq.base;

import com.fqh.mq.rocketMq.Constants;
import java.util.List;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * @author fqh
 * @Description: 异步发送消息
 * @date 2020/8/23下午12:07
 */
public class ProduceAyscSendMsg {

  public static void main(String[] args)
      throws MQClientException, RemotingException, InterruptedException {
    DefaultMQProducer producer = new DefaultMQProducer("produceGroup");
    producer.setNamesrvAddr(Constants.NameServerAddr);
    producer.start();

    Message message = new Message("topic02", "xxoo".getBytes());
    //消息异步发送
    producer.send(message, new SendCallback() {
      @Override
      public void onSuccess(SendResult sendResult) {
        System.out.println("消息发送成功");
        System.out.println(sendResult);
      }

      @Override
      public void onException(Throwable e) {
          e.printStackTrace();
          System.out.println(e.getMessage());
      }
    });
  }
}
