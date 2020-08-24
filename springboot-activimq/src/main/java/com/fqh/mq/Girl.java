package com.fqh.mq;

import java.io.Serializable;

/**
 * @author fqh
 * @Description:
 * @date 2020/8/14上午11:02
 */
public class Girl implements Serializable {
  private String name;
  private Integer age;
  private Float price;

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

  public Float getPrice() {
    return price;
  }

  public void setPrice(Float price) {
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
