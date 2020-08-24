package com.fqh.mq.kafka;

import com.fqh.mq.kafka.service.ISendMsg;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.core.KafkaOperations.OperationsCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author fqh
 * @Description:
 * @date 2020/8/20下午6:31
 */
@SpringBootTest(classes = SpringbootKafkaApplication.class)
@RunWith(SpringRunner.class)
public class TestKafkaProduce {
  @Autowired
  KafkaTemplate<String,String> kafkaTemplate;
  @Autowired
  ISendMsg sendMsg;

  @Test
  public void testSendMsg(){
    kafkaTemplate.send(new ProducerRecord<>("topic01","key01","goodluck"));
  }

  /*
  发送事务消息--与spring @Transactional注解一起用
   */
  @Test
  public void testSendTransactionalMsg(){
   sendMsg.sendTransactionalMsg();
  }
  /*
   发送事务消息
    */
  @Test
  public void testSendTransactionalMsg2(){
    kafkaTemplate.executeInTransaction(new OperationsCallback<String, String, Object>() {
      @Override
      public Object doInOperations(KafkaOperations<String, String> kafkaOperations) {
        return kafkaTemplate.send("topic01","fqh_01","lxl");
      }
    });
  }


}
