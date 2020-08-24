package com.fqh.mq.kafka.interceptors;

import java.util.Map;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

/**
 * @author fqh
 * @Description:
 * @date 2020/8/17下午6:22
 */
public class MyInterceptor implements ProducerInterceptor {

  @Override
  public ProducerRecord onSend(ProducerRecord record) {
    //可以更改消息的消息头等相关信息

    return new ProducerRecord(record.topic(),record.key(),record.value()+"--fqh测试");
  }

  @Override
  public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
    System.out.println("config");
  }

  @Override
  public void close() {
    System.out.println("close");
  }

  @Override
  public void configure(Map<String, ?> configs) {
    System.out.println("config");
  }
}
