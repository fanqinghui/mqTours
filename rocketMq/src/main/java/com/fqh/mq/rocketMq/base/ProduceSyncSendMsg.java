package com.fqh.mq.rocketMq.base;

import com.fqh.mq.rocketMq.Constants;
import java.util.ArrayList;
import java.util.List;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * @author fqh
 * @Description: 默认send是同步方式
 * @date 2020/8/22下午10:41
 */
public class ProduceSyncSendMsg {

  public static void main(String[] args)
      throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
    DefaultMQProducer producer = new DefaultMQProducer("topicTest");

    //设置produce的nameServer
    producer.setNamesrvAddr(Constants.NameServerAddr);
    producer.setSendMsgTimeout(3000);
    producer.setRetryTimesWhenSendFailed(3);
    producer.start();

    //构建发送的消息
    String topic="topic01";
    List<Message> messages=new ArrayList<>();
    for(int i=0;i<5;i++){
      byte[] body=("good"+i).getBytes();
      Message message = new Message(topic,body);
      messages.add(message);
    }
    //发送list--同步发送消息
    SendResult result = producer.send(messages);
    //发送单条消息
    //producer.send(message);
   // producer.shutdown();
  }
}
