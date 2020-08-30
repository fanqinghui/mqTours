package com.fqh.mq.rocketmq.mqListener;

import com.fqh.mq.rocketmq.domain.User;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @author fqh
 * @Description: 字符串消息发送
 * @date 2020/8/27下午2:49
 */
@Service
@RocketMQMessageListener(nameServer = "${rocketmq.name-server}",topic = "userTopic", consumerGroup = "user_consumer")
public class UserConsumer implements RocketMQListener<User> {

  @Override
  public void onMessage(User user) {
    System.out.println("User Message is:"+user);
  }
}
