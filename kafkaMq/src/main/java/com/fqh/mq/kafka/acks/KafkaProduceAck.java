package com.fqh.mq.kafka.acks;

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
public class KafkaProduceAck {

  public static void main(String[] args) throws InterruptedException {
    /**
     * 创建kafkaproducer
     */
    Properties properties = new Properties();
    properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    //设置 kafka acks 为all 以及retry次数
    properties.put(ProducerConfig.ACKS_CONFIG, "all");
    properties.put(ProducerConfig.RETRIES_CONFIG, 3);
    //模拟kafaka 检测超时时间为1ms
    properties.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, 1);

    KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(properties);

    ProducerRecord<String, String> record = new ProducerRecord<String, String>("topic01", "ack",
        "tet ack value");
    kafkaProducer.send(record);
    kafkaProducer.flush();
    kafkaProducer.close();
  }
}

