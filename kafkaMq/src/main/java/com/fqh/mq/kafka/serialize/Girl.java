package com.fqh.mq.kafka.serialize;

import java.io.Serializable;

/**
 * @author fqh
 * @Description: 序列化测试类
 * @date 2020/8/17下午5:28
 */
public class Girl implements Serializable {
  private String name;
  private Integer age;
  private Double price;

  public Girl(String name, Integer age, Double price) {
    this.name = name;
    this.age = age;
    this.price = price;
  }

  public Girl() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return "Girl{" +
        "name='" + name + '\'' +
        ", age=" + age +
        ", price=" + price +
        '}';
  }
}
