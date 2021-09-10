package com.zhao.rmq.order;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.List;

public class MQOrderProducer {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("producer_part_order");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();
        Message msg = null;
        List<MessageQueue> queues = producer.fetchPublishMessageQueues("producer_part_order");
        System.out.println(queues.size());
        MessageQueue queue =null;
        for (int i=0;i<20;i++){
             queue =queues.get(i%4);
             msg = new Message("producer_part_order",("order_create"+i).getBytes());
             producer.send(msg,queue);
             msg = new Message("producer_part_order",("order_payed"+i).getBytes());
             producer.send(msg,queue);
             msg = new Message("producer_part_order",("order_ship"+i).getBytes());
             producer.send(msg,queue);
        }
        producer.shutdown();
    }
}
