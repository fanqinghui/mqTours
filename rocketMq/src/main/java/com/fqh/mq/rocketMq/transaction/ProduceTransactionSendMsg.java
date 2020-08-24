package com.fqh.mq.rocketMq.transaction;

import com.fqh.mq.rocketMq.Constants;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @author fqh
 * @Description: 事务发送消息
 * @date 2020/8/23下午8:54
 */
public class ProduceTransactionSendMsg {

  public static void main(String[] args) throws MQClientException {
    TransactionMQProducer producer = new TransactionMQProducer("transGroup");
    producer.setNamesrvAddr(Constants.NameServerAddr);
    producer.setTransactionListener(new TransactionListener() {
      @Override
      public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        System.out.println("==executeLocalTransaction:msg:"+new String(msg.getBody()));
        //执行具体业务。。可能很多方法
        return LocalTransactionState.UNKNOW;
      }
      /**
       * 回调
       */
      @Override
      public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        System.out.println("==checkLocalTransaction:msg:"+new String(msg.getBody()));
        //return LocalTransactionState.UNKNOW;
        return LocalTransactionState.COMMIT_MESSAGE;
      }
    });
    producer.start();
    producer.sendMessageInTransaction(new Message("transTopic","事务消息".getBytes()),null);

  }
}
