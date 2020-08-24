package com.fqh.mq.config;

import javax.jms.ConnectionFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

/**
 * @author fqh
 * @Description: activeMq配置类
 * @date 2020/8/14上午10:30
 */
@Configuration
public class ActiveMqConfig {

  @Bean
  public JmsListenerContainerFactory<?> jmsListenerContainerTopic(
      ConnectionFactory connectionFactory) {
    DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
    bean.setPubSubDomain(true);
    bean.setConnectionFactory(connectionFactory);
    return bean;
  }

  // queue模式的ListenerContainer
  @Bean
  public JmsListenerContainerFactory<?> jmsListenerContainerQueue(
      ConnectionFactory connectionFactory) {
    DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
    bean.setConnectionFactory(connectionFactory);
    return bean;
  }
}
