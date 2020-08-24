package com.fqh.mq.kafka.offset;

import java.time.Duration;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Properties;
import java.util.regex.Pattern;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

/**
 * @author fqh
 @Description: kafaka 消息consumer设置auto-offet-reset-config为 latest- 默认值
 * @date 2020/8/17下午2:04
 */
public class KafkaConsumerOffsetDemo01 {

  public static void main(String[] args) {
    /**
     * 创建kafkaproducer
     */
    Properties properties=new Properties();
    properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
    properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
    properties.put(ConsumerConfig.GROUP_ID_CONFIG,"g1");
    //消费者第一次连接topic时候，没有偏移量的情况下，设置为latet（默认），代表改connumer从topic的下一条消息开订阅
    properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"latest");

    KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(properties);
    kafkaConsumer.subscribe(Arrays.asList("topic01"));

    while (true){
      ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(1));
      if(!consumerRecords.isEmpty()){
        Iterator<ConsumerRecord<String, String>> iterator = consumerRecords.iterator();
        while (iterator.hasNext()){
          ConsumerRecord<String, String> record = iterator.next();
          String topic = record.topic();
          String key = record.key();
          String value = record.value();
          long offset = record.offset();
          int partition = record.partition();
          System.out.println("key:"+key+",value:"+value+",partition:"+partition+",offset:"+offset);
        }
      }
    }

  }
}

