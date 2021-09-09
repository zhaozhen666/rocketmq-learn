package com.zhao.rmq.retry;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class MQRetryConsumer {
    public static void main(String[] args) throws MQClientException {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("mq_balancer_pushconsumer");
        consumer.setNamesrvAddr("127.0.0.1:9876");
        consumer.setConsumeMessageBatchMaxSize(1);
        consumer.setConsumeThreadMin(1);
        consumer.setConsumeThreadMax(1);
        consumer.subscribe("mq_balancer","*");
        consumer.setMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                for (MessageExt msg : msgs) {
                    System.out.println(msg);
                }
                return null;
            }
        });
        consumer.start();
    }
}
