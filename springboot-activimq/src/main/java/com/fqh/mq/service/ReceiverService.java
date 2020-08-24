package com.fqh.mq.service;

import com.fqh.mq.Girl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * @author fqh
 * @Description:
 * @date 2020/8/14上午10:54
 */
@Service
public class ReceiverService {

  @Autowired
  JmsTemplate jmsTemplate;

  @JmsListener(destination="user",containerFactory="jmsListenerContainerTopic")
  public void getMqReceiveMsg(String msg){
    System.out.println("收到消息:"+msg);
  }
  @JmsListener(destination="girl",containerFactory="jmsListenerContainerTopic")
  public void getMqReceiveGirlMsg(Girl girl){
    System.out.println("收到消息:"+girl);
  }

}
