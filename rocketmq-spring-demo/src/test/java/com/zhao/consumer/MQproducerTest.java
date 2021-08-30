package com.zhao.consumer;

import com.zhao.producer.RocketMQProducerApplication;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RocketMQProducerApplication.class})
public class MQproducerTest {
    @Autowired
    RocketMQTemplate rocketMQTemplate;

    @Test
    public void send(){
        rocketMQTemplate.convertAndSend("rocketmq-spring","hello rocketmq-spring");
    }

    @Test
    public void sendmessages(){
        for (int i=0;i<100;++i){
            rocketMQTemplate.convertAndSend("rocketmq-spring","hello rocketmq-spring"+i);
        }
    }
}
