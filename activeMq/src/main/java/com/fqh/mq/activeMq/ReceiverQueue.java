package com.fqh.mq.activeMq;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSession;

/**
 * @author fqh
 * @Description: 消息消费者
 * @date 2020/8/12下午3:28
 */
public class ReceiverQueue {

  public static void main(String[] args) throws JMSException, InterruptedException {
    ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
        ActiveMQConnectionFactory.DEFAULT_USER,
        ActiveMQConnectionFactory.DEFAULT_PASSWORD,
        "tcp://127.0.0.1:61616"
    );
    Connection connection = connectionFactory.createConnection();
    connection.start();
    boolean transacted = false;
    Session session = connection.createSession(transacted, ActiveMQSession.AUTO_ACKNOWLEDGE);
    Queue testQueue = session.createQueue("testQueue");
    MessageConsumer consumer = session.createConsumer(testQueue);
    consumer.setMessageListener(new MyMessageListener());
   /* while (true){
      TextMessage receive = (TextMessage) consumer.receive();
      System.out.println("msg1:"+receive.getText());
      Thread.sleep(1000);
    }*/
  }
}
