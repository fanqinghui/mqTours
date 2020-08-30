package com.fqh.mq.rocketmq.domain;

import java.io.Serializable;

/**
 * @author fqh
 * @Description: user
 * @date 2020/8/27下午3:25
 */
public class User implements Serializable {
    private Long id;
    private String name;
    private Integer age;

  public User(Long id, String name, Integer age) {
    this.id = id;
    this.name = name;
    this.age = age;
  }

  public User() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
}
