package com.fqh.mq.kafka.serialize;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

/**
 * @author fqh
 * @Description: kafaka 消息provider
 * @date 2020/8/17下午2:04
 */
public class KafkaProduceDemo {

  public static void main(String[] args) throws InterruptedException {
    /**
     * 创建kafkaproducer
     */
    Properties properties=new Properties();
    properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
    properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
    properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,MySerializer.class.getName());
    KafkaProducer<String, Girl> kafkaProducer = new KafkaProducer<>(properties);

    for(int i=0;i<10;i++){
      Thread.sleep(100);
      ProducerRecord<String,Girl> record=new ProducerRecord<>("topic02","key"+i,new Girl("lxl"+i,20,
          (double) (100 + i)));
      kafkaProducer.send(record);
    }
  }
}

