package com.fqh.mq.rocketMq;

/**
 * @author fqh
 * @Description:
 * @date 2020/8/22下午10:52
 */
public interface Constants {

  //String NameServerAddr="127.0.0.1:9876";
  String NameServerAddr="47.92.144.90:9876";

  /*nohup sh  mqnamesrv -n "47.92.144.90:9876" &
  nohup sh mqbroker -n 47.92.144.90:9876 -c ../conf/broker.conf autoCreateTopicEnable=true &*/
}
