package com.fqh.mq.kafka.dml;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;


/** kafaka 基础api--- topic DML
 * @author fqh
 * @Description:
 * @date 2020/8/17上午11:27
 */
public class KafkaTopicDML {

  public static void main(String[] args)
      throws ExecutionException, InterruptedException, IOException {
    Properties pros=new Properties();
    pros.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
    KafkaAdminClient adminClient =(KafkaAdminClient) KafkaAdminClient.create(pros);

    //topic创建-默认异步
    CreateTopicsResult createTopic1Result = adminClient
        .createTopics(Arrays.asList(new NewTopic("topic03", 3, (short) 1)));
    CreateTopicsResult createTopic2Result = adminClient
        .createTopics(Arrays.asList(new NewTopic("topic02", 3, (short) 1)));
    //createTopicsResult.all().get();//同步创建
    //topic删除"topic02"
    //adminClient.deleteTopics(Arrays.asList("topic03","topic01","topic02"));
    /*DescribeTopicsResult topic01 = adminClient.describeTopics(Arrays.asList("topic01"));
    Map<String, TopicDescription> topicDescriptionMap = topic01.all().get();
    for (Map.Entry<String,TopicDescription> entry:topicDescriptionMap.entrySet()){
      System.out.println(entry.getKey()+"\t"+entry.getValue());
    }
*/
    ListTopicsResult topicsResult = adminClient.listTopics();
    Set<String> names = topicsResult.names().get();
    for(String name:names){
      System.out.println(name);
    }
    // 关闭kafkaadmin
    adminClient.close();
  }
}
