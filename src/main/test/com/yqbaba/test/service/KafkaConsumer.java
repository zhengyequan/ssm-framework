package com.yqbaba.test.service;


import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class KafkaConsumer implements Runnable {

    private String zookeeperConnect;
    private String groupId;
    private String topic;
    private boolean autoCommit;
    private ConsumerConnector consumer;

    public void setZookeeperConnect(String zookeeperConnect) {
        this.zookeeperConnect = zookeeperConnect;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setAutoCommit(boolean autoCommit) {
        this.autoCommit = autoCommit;
    }

    public void init() {
        Properties props = new Properties();
        props.put("zookeeper.connect", zookeeperConnect);
        props.put("group.id", groupId);
        props.put("enable.auto.commit", autoCommit);
        ConsumerConfig conf = new ConsumerConfig(props);
        consumer = Consumer.createJavaConsumerConnector(conf);
    }

    @Override
    public void run() {
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(topic, new Integer(1));
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
        List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);

        KafkaStream<byte[], byte[]> stream = streams.get(0);
        ConsumerIterator<byte[], byte[]> it = stream.iterator();
        while (it.hasNext()) {
            try {
                System.out.println("message: " + new String(it.next().message(), "utf-8"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
