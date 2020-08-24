package com.fqh.mq.kafka.serialize;

import java.time.Duration;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

/**
 * @author fqh
 * @Description: kafaka 消息consumer
 * @date 2020/8/17下午2:04
 */
public class KafkaConsumerDemo {

  public static void main(String[] args) {
    /**
     * 创建kafkaproducer
     */
    Properties properties=new Properties();
    properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
    properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,MyDesSerializer.class.getName());
    properties.put(ConsumerConfig.GROUP_ID_CONFIG,"group1");
    KafkaConsumer<String, Girl> kafkaConsumer = new KafkaConsumer<>(properties);
    kafkaConsumer.subscribe(Arrays.asList("topic02"));

    while (true){
      ConsumerRecords<String, Girl> consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(1));
      if(!consumerRecords.isEmpty()){
        Iterator<ConsumerRecord<String, Girl>> iterator = consumerRecords.iterator();
        while (iterator.hasNext()){
          ConsumerRecord<String, Girl> record = iterator.next();
          String topic = record.topic();
          String key = record.key();
          Girl girl = record.value();
          long offset = record.offset();
          int partition = record.partition();
          System.out.println("key:"+key+",value:"+girl+",partition:"+partition+",offset:"+offset);
        }
      }
    }

  }
}

