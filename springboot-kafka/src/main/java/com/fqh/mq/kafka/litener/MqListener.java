package com.fqh.mq.kafka.litener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.KafkaListeners;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * @author fqh
 * @Description:
 * @date 2020/8/20下午6:22
 */
@Component
public class MqListener {


  @KafkaListeners(value = {
      @KafkaListener(topics={"topic01"})
  })
  public void receiveTopic01(ConsumerRecord<String,String> record){
    System.out.println("topic01:key:"+record.key()+" value:"+record.value());
  }

  @KafkaListeners(value = {
      @KafkaListener(topics={"topic02"})
  })
  @SendTo("topic03")
  public void receiveTopic02(ConsumerRecord<String,String> record){
    System.out.println("topic02:key:"+record.key()+" value:"+record.value());
  }

  @KafkaListeners(value = {
      @KafkaListener(topics={"topic03"})
  })
  public void receiveTopic03(ConsumerRecord<String,String> record){
    System.out.println("topic03:key:"+record.key()+" value:"+record.value());
  }

}
