package com.zhao.producer;

import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RocketMQProducerApplication.class})
public class MQProducerTest {
    @Autowired
    RocketMQTemplate rocketMQTemplate;

    @Test
    public  void sendMessage(){
        rocketMQTemplate.convertAndSend("rocketmq-spring",
                "hello rocketmq-spring");
    }

    @Test
    public  void sendMessages(){
        for (int i=0;i<100;++i){
            rocketMQTemplate.convertAndSend("rocketmq-spring",
                    "hello rocketmq-spring"+i);
        }

    }

//    @Test
//    public  void test(){
//        DefaultMQPullConsumer consumer = new DefaultMQPullConsumer();
//        consumer.setMessageModel(MessageModel.BROADCASTING);
//        consumer.setMessageModel(MessageModel.CLUSTERING);
//
//
//        DefaultMQPushConsumer pushConsumer =new DefaultMQPushConsumer();
//        pushConsumer.setConsumeThreadMax();
//        pushConsumer.setConsumeThreadMin();
//        pushConsumer.subscribe();
//        pushConsumer.setConsumeMessageBatchMaxSize();
//    }
}
