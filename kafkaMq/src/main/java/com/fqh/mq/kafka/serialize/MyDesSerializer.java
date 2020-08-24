package com.fqh.mq.kafka.serialize;

import java.util.Map;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.kafka.common.serialization.Deserializer;

/**
 * @author fqh
 * @Description: 自己定义的对象序列号 kafaka替换类
 * @date 2020/8/17下午5:22
 */
public class MyDesSerializer implements Deserializer<Object> {

  /**
   * Configure this class.
   *
   * @param configs configs in key/value pairs
   * @param isKey whether is for key or value
   */
  @Override
  public void configure(Map configs, boolean isKey) {
    System.out.println("desSeri:config");
  }

  /**
   * Close this serializer.
   *
   * This method must be idempotent as it may be called multiple times.
   */
  @Override
  public void close() {
    System.out.println("desSeri:close");
  }

  /**
   * Deserialize a record value from a byte array into a value or object.
   *
   * @param topic topic associated with the data
   * @param data serialized bytes; may be null; implementations are recommended to handle null by
   * returning a value or null rather than throwing an exception.
   * @return deserialized typed data; may be null
   */
  @Override
  public Object deserialize(String topic, byte[] data) {
    return SerializationUtils.deserialize(data);
  }
}
