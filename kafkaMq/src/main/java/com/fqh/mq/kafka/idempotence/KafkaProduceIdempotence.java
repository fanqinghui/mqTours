package com.fqh.mq.kafka.idempotence;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

/**
 * @author fqh
 * @Description: kafaka 消息幂等
 * @date 2020/8/17下午2:04
 */
public class KafkaProduceIdempotence {

  public static void main(String[] args) throws InterruptedException {
    /**
     * 创建kafkaproducer
     */
    Properties properties=new Properties();
    properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
    properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());

    properties.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG,true);//开启幂等
    properties.put(ProducerConfig.ACKS_CONFIG,"all");
    properties.put(ProducerConfig.RETRIES_CONFIG,3);
    properties.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION,1);

    KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(properties);

    for(int i=0;i<10;i++){
      Thread.sleep(100);
      ProducerRecord<String,String> record=new ProducerRecord<String, String>("topic01","key"+i,""+i);
      kafkaProducer.send(record);
    }
  }
}

