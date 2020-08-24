package com.fqh.mq.activeMq;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSession;

/**
 * @author fqh
 * @Description: 消息sender
 * @date 2020/8/12下午2:10
 */
public class SenderQueue {

  public static void main(String[] args) throws JMSException, InterruptedException {
    //创建工厂
    ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
        "tcp://127.0.0.1:61616");
    //获取连接
    Connection connection = connectionFactory.createConnection("admin", "admin");
    //创建session
    boolean transacted = false;
    Session session = connection.createSession(transacted, ActiveMQSession.AUTO_ACKNOWLEDGE);
    //当transacted设置成了true，后面的Session的acknowledgeMode 设置成啥都不好使
    Queue queue = session.createQueue("testQueue");
    MessageProducer producer = session.createProducer(queue);
    //发送消息
    for (int i = 0; i < 100; i++) {
      TextMessage message = session.createTextMessage("msg:" + i);
      if (i % 10 == 0) {
        producer.send(message, DeliveryMode.PERSISTENT, 9, 1000 * 6 * 24);
      } else {
        producer.send(message);
      }
      //Thread.sleep(3000L);
      //session.commit();//如果createSession的事务transacted 设置为true，这里send消息就必须用session.commit()进行提交
    }
    connection.close();
    System.out.println("has sending");
  }
}
