package com.zhao.tagfilter;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;

public class MQConsumerSimpleTagFilter {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("producer_tagfilter_simple_03");

        consumer.setNamesrvAddr("127.0.0.1:9876");
//        consumer.subscribe("simple_tagfilter","*");
//        consumer.subscribe("simple_tagfilter","tag-1");
        consumer.subscribe("simple_tagfilter","tag-1||tag-0");

        consumer.setMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                final MessageQueue messageQueue = context.getMessageQueue();
                final String topic =messageQueue.getTopic();
                final  String brokerName = messageQueue.getBrokerName();
                 final int queueId = messageQueue.getQueueId();
                System.out.println("brokeName "+brokerName+" topic "+topic+"queueId "+queueId);
                for (MessageExt msg:msgs){
                    System.out.println(msg);
                }

                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();

    }
}
