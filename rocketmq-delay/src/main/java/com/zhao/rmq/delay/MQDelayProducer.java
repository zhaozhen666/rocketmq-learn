package com.zhao.rmq.delay;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

public class MQDelayProducer {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("producer_tagfilter");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();


        for (int i=0;i<20;i++){
            Message message = new Message();
            message.setTopic("mq_delay");
            message.setBody(("hello rmq delay"+i).getBytes());
            message.setDelayTimeLevel(i);
            producer.send(message);
        }

        producer.shutdown();
    }
}
