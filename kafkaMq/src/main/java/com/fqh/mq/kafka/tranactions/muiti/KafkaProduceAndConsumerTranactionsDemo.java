package com.fqh.mq.kafka.tranactions.muiti;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.protocol.types.Field.Str;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

/**
 * @author fqh
 * @Description: kafaka 消息事务
 * @date 2020/8/17下午2:04
 */
public class KafkaProduceAndConsumerTranactionsDemo {

  public static void main(String[] args) {
    KafkaProducer<String, String> kafkaProducer = buildKafkaProduce();
    KafkaConsumer<String, String> kafkaConsumer = buildKafkaConsumer("g1");
    kafkaConsumer.subscribe(Arrays.asList("topic03"));

    //开启事务
    kafkaProducer.initTransactions();
    while (true) {
      ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofSeconds(1));
      Map<TopicPartition, OffsetAndMetadata> offset = new HashMap<>();
      while (!records.isEmpty()) {
        System.out.println("收到消息数量:" + records.count());
        Iterator<ConsumerRecord<String, String>> recordIterator = records.iterator();
        try {
          kafkaProducer.beginTransaction();
          while (recordIterator.hasNext()) {
            //业务逻辑
            ConsumerRecord<String, String> record = recordIterator.next();
            kafkaProducer.send(new ProducerRecord<>("topic02",record.key(), record.value() + "_byFqhHandler"));
            //手动向kafka server提交offset消息
            offset.put(new TopicPartition(record.topic(), record.partition()),new OffsetAndMetadata(record.offset() + 1));
          }
          //提交事务这里有点不同
          kafkaProducer.sendOffsetsToTransaction(offset, "g1");//提交消费者的offset
          kafkaProducer.commitTransaction();
        } catch (Exception e) {
          System.out.println("异常:" + e.getMessage());
          kafkaProducer.abortTransaction();
        }
      }
    }
  }

  /**
   * 构建kafka的produce
   */
  static KafkaProducer<String, String> buildKafkaProduce() {
    Properties properties = new Properties();
    properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    //事务id
    properties.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "trans_id" + UUID.randomUUID());
   /* properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 1024);//默认批处理处理数量，达到数量既可以发送
    //等等5ms，如果batch——size——config不足1024，到了5毫秒，也进行发送。
    properties.put(ProducerConfig.LINGER_MS_CONFIG, 5);
    //开启幂等与重复机制
    properties.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
    properties.put(ProducerConfig.ACKS_CONFIG, "all");
    properties.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, 20000);*/
    /*properties.put(ProducerConfig.RETRIES_CONFIG, 3);
    properties.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, 1);*/
    return new KafkaProducer<String, String>(properties);
  }

  /**
   * 构建kafka的conumer
   */
  static KafkaConsumer<String, String> buildKafkaConsumer(String groupId) {
    Properties properties = new Properties();
    properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
    //开启事务日志级别-为read-commited
    properties.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_committed");
    //必须关闭消费端的offset自动提交
    properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
    return new KafkaConsumer<String, String>(properties);
  }
}

