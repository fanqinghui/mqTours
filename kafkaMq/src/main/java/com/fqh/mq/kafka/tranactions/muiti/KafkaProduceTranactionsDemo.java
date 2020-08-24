package com.fqh.mq.kafka.tranactions.muiti;

import java.util.Properties;
import java.util.UUID;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

/**
 * @author fqh
 * @Description: kafaka 消息事务
 * @date 2020/8/17下午2:04
 */
public class KafkaProduceTranactionsDemo {

  public static void main(String[] args) throws InterruptedException {
    /**
     * 创建kafkaproducer
     */
    Properties properties = new Properties();
    properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    //事务id
    properties.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "trans_id" + UUID.randomUUID());
/*    properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 1024);//默认批处理处理数量，达到数量既可以发送
    //等等100ms，如果batch——size——config不足1024，到了100 毫秒，也进行发送。
    properties.put(ProducerConfig.LINGER_MS_CONFIG, 100);
    //开启幂等与重复机制
    properties.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
    properties.put(ProducerConfig.ACKS_CONFIG, "all");
    properties.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, 20000);*/
    /*properties.put(ProducerConfig.RETRIES_CONFIG,3);
    properties.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION,1);*/

    KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(properties);
    //开启事务
    kafkaProducer.initTransactions();
    try {
      kafkaProducer.beginTransaction();
      for (int i = 0; i < 10; i++) {
        /*if (i == 8) {
          int err = 1 / 0;
        }*/
        ProducerRecord<String, String> record = new ProducerRecord<String, String>("topic03",
            "key" + i, "succes" + i);

        kafkaProducer.send(record);
        kafkaProducer.flush();
      }
      kafkaProducer.commitTransaction();
    } catch (Exception e) {
      e.printStackTrace();
      kafkaProducer.abortTransaction();
    }

    kafkaProducer.close();
  }
}

