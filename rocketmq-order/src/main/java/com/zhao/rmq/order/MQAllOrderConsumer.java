package com.zhao.rmq.order;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class MQAllOrderConsumer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer =new DefaultMQPushConsumer("producer_global_order");
        consumer.setNamesrvAddr("127.0.0.1:9876");
        consumer.subscribe("producer_global_order","*");
        consumer.setConsumeMessageBatchMaxSize(1);
        consumer.setConsumeThreadMax(1);
        consumer.setConsumeThreadMin(1);
        consumer.setPullBatchSize(1);

        consumer.setMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {

                for (MessageExt msg:msgs){
                    System.out.println(msg.getTopic()+"\t"+msg.getQueueId()+"\t"+msg.getMsgId()+"\t"+new String(msg.getBody()));
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        consumer.start();
    }
}
