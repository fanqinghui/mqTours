package com.fqh.mq.rocketMq.fifo;

import com.fqh.mq.rocketMq.Constants;
import java.util.List;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.selector.SelectMessageQueueByHash;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * @author fqh
 * @Description: 发送顺序消息
 * @date 2020/8/23下午10:23
 */
public class FiFoProduce {

  public static void main(String[] args)
      throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
    DefaultMQProducer pro =new DefaultMQProducer("g1");
    pro.setNamesrvAddr(Constants.NameServerAddr);
    pro.start();
    //自定义实现messageQueueSelector
    for(int i=0;i<20;i++){
      pro.send(new Message("fifoTopic", ("顺序消息"+i).getBytes()), new MessageQueueSelector() {
        @Override
        public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
          System.out.println("selectQueue:"+msg.toString());
          System.out.println("arg"+arg);
          return mqs.get(0);
        }
      },1);
    }
    //hashQueueSelector
    //pro.send(msg,new SelectMessageQueueByHash(),"业务key，用于hash到具体的queue,顺序消息要求这个参数一样");
  }
}
