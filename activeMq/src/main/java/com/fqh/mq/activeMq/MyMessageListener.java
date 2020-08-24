package com.fqh.mq.activeMq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.xml.soap.Text;

/**
 * @author fqh
 * @Description:
 * @date 2020/8/12下午11:13
 */
public class MyMessageListener implements MessageListener {

  public void onMessage(Message message) {
    if (message instanceof TextMessage) {
      TextMessage textMessage = (TextMessage) message;
      try {
        System.out.println(textMessage.getText());
      } catch (JMSException e) {
        e.printStackTrace();
      }
    }
  }
}
