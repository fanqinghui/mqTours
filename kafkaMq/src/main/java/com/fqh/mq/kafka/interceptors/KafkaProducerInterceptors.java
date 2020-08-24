package com.fqh.mq.kafka.interceptors;

import com.fqh.mq.kafka.patitioner.MyPartitioner;
import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

/**
 * @author fqh
 * @Description: 负载均衡patition策略--自定义分区
 * @date 2020/8/17下午3:46
 */
public class KafkaProducerInterceptors {

  public static void main(String[] args) throws InterruptedException {
    /**
     * 创建kafkaproducer
     */
    Properties properties=new Properties();
    properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
    properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
    properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG,MyInterceptor.class.getName());
    KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(properties);

    for(int i=0;i<10;i++){
      Thread.sleep(100);
      ProducerRecord<String,String> record=new ProducerRecord<String, String>("topic02","key"+i,""+i);
      kafkaProducer.send(record);
    }
  }
}
