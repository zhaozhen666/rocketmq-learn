package com.zhao.rmq.retry;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class MQNoOrderlyRetryConsumer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("mq_balancer_pushconsumer");
        consumer.setNamesrvAddr("127.0.0.1:9876");
        consumer.subscribe("mq_balancer","*");
        consumer.setMaxReconsumeTimes(20);
        consumer.setMessageListener(new MyConcurrentlyMessageListener());
        consumer.start();
    }
}
