package com.fqh.mq.kafka.service.impl;

import com.fqh.mq.kafka.service.ISendMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author fqh
 * @Description:
 * @date 2020/8/21上午12:38
 */
@Service
public class SendMsgImpl implements ISendMsg {

  @Autowired
  KafkaTemplate<String,String> kafkaTemplate;

  @Override
  @Transactional
  public void sendTransactionalMsg() {
    kafkaTemplate.send("topic01","fqh","zj");
  }
}
