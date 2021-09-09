package com.zhao.rmq.retry;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;
//需要重试时可以采用三种方式
public class MyConcurrentlyMessageListener implements MessageListenerConcurrently {
    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        for (MessageExt msg : msgs) {
            System.out.println(msg);
        }
        //需要重试的时候的时候的设置
        //        return ConsumeConcurrentlyStatus.RECONSUME_LATER;
//        throw  new RuntimeException("consumer failed,todo retry");
        return null;

        //不需要重试直接catch掉异常然后返回
        //return  ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
