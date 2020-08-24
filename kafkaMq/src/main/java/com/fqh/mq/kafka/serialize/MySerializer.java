package com.fqh.mq.kafka.serialize;

import java.io.Serializable;
import java.util.Map;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.kafka.common.serialization.Serializer;

/**
 * @author fqh
 * @Description: 自己定义的对象序列号 kafaka替换类
 * @date 2020/8/17下午5:22
 */
public class MySerializer implements Serializer {

  /**
   * Configure this class.
   *
   * @param configs configs in key/value pairs
   * @param isKey whether is for key or value
   */
  @Override
  public void configure(Map configs, boolean isKey) {
    System.out.println("seri:config");
  }

  /**
   * Convert {@code data} into a byte array.
   *
   * @param topic topic associated with data
   * @param data typed data
   * @return serialized bytes
   */
  @Override
  public byte[] serialize(String topic, Object data) {
    return SerializationUtils.serialize((Serializable) data);
  }

  /**
   * Close this serializer.
   *
   * This method must be idempotent as it may be called multiple times.
   */
  @Override
  public void close() {
    System.out.println("seri:close");
  }
}
