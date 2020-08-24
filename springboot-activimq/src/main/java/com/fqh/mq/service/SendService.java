package com.fqh.mq.service;

import com.fqh.mq.Girl;
import javax.jms.Message;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * @author fqh
 * @Description:
 * @date 2020/8/14上午10:48
 */
@Service
public class SendService {

  @Autowired
  JmsTemplate jmsTemplate;
  @Autowired
  JmsMessagingTemplate jmsMessagingTemplate;

  public void send(String destination,String msg){
    jmsMessagingTemplate.convertAndSend(destination,msg);
  }

  public void sendObj(String destination, Girl girl){
    jmsMessagingTemplate.convertAndSend(destination,girl);
  }


}
