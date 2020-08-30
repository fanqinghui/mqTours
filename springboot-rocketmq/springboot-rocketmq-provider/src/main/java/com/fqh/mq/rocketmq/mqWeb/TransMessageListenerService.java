package com.fqh.mq.rocketmq.mqWeb;

import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;

/**
 * @author fqh
 * @Description:
 * @date 2020/8/30上午10:27
 */
@RocketMQTransactionListener
public class TransMessageListenerService implements RocketMQLocalTransactionListener {

  @Override
  public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
    System.out.println("executeLocalTransaction:"+message.getPayload().toString());
    return RocketMQLocalTransactionState.UNKNOWN;
  }

  @Override
  public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
    System.out.println("checkLocalTransaction:"+message.getPayload().toString());
    return RocketMQLocalTransactionState.COMMIT;
  }
}
