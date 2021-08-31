package com.zhao.tagfilter;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;

public class MQConsumerTagSqlFilter {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("producer_tagfilter_sql_01");

        consumer.setNamesrvAddr("127.0.0.1:9876");
//        consumer.subscribe("simple_tagfilter","*");
//        consumer.subscribe("simple_tagfilter","tag-1");
        consumer.subscribe("sql_tagfilter", MessageSelector.bySql("key ='v0'"));
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
