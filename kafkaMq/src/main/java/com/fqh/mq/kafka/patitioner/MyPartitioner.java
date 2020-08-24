package com.fqh.mq.kafka.patitioner;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.utils.Utils;

/**
 * @author fqh
 * @Description: 自己实现patition策略,集成Patitioner
 * @date 2020/8/17下午4:48
 */
public class MyPartitioner implements Partitioner {
  private AtomicInteger counter=new AtomicInteger(0);
  /**
   * Compute the partition for the given record.
   *
   * @param topic The topic name
   * @param key The key to partition on (or null if no key)
   * @param keyBytes The serialized key to partition on( or null if no key)
   * @param value The value to partition on or null
   * @param valueBytes The serialized value to partition on or null
   * @param cluster The current cluster metadata
   */
  @Override
  public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes,
      Cluster cluster) {
    //获取所有可用分区数
    List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
    int numPartitions = partitions.size();
    if (keyBytes == null) {
      //key==null的情况下轮询策略
      int andIncrement = counter.getAndIncrement();
      return andIncrement&Integer.MAX_VALUE %numPartitions;
     /* int nextValue = nextValue(topic);
      List<PartitionInfo> availablePartitions = cluster.availablePartitionsForTopic(topic);
      if (availablePartitions.size() > 0) {
        int part = Utils.toPositive(nextValue) % availablePartitions.size();
        return availablePartitions.get(part).partition();
      } else {
        // no partitions are available, give a non-available partition
        return Utils.toPositive(nextValue) % numPartitions;
      }*/
    } else {
      return Utils.toPositive(Utils.murmur2(keyBytes)) % numPartitions;
    }
  }

  /**
   * This is called when partitioner is closed.
   */
  @Override
  public void close() {
    System.out.println("close");
  }

  /**
   * Configure this class with the given key-value pairs
   */
  @Override
  public void configure(Map<String, ?> configs) {
    System.out.println("config");
  }
}
