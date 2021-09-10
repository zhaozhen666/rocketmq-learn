package com.zhao.rmq.order;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
//mqadmin updateTopic -b 127.0.0.1:10911 -n localhost:9876 -r 1 -t producer_global_order -w 1
public class MQAllOrderProducer {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("producer_global_order");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();
        Message message=null;
        for (int i=0;i<100;i++){
            message = new Message("producer_global_order",("global order_"+i).getBytes());
            producer.send(message);
        }
        producer.shutdown();
    }
}
