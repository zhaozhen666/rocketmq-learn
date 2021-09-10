package com.zhao.rmq.order;

import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.PullResult;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.List;
import java.util.Set;

public class MQOrderConsumer {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQPullConsumer consumer = new DefaultMQPullConsumer("consumer_part_order");
        consumer.setNamesrvAddr("127.0.0.1:9876");
        consumer.start();
        Set<MessageQueue> messageQueues = consumer.fetchSubscribeMessageQueues("producer_part_order");
        System.out.println(messageQueues.size());
        for (MessageQueue queue:messageQueues){
            long nextBeginOffset = 0;
            System.out.println("===================================");
            do {
                PullResult pullResult  = consumer.pull(queue,"*",nextBeginOffset,1);
                if (pullResult==null||pullResult.getMsgFoundList()==null)
                    break;
                nextBeginOffset= pullResult.getNextBeginOffset();
                List<MessageExt>  exts= pullResult.getMsgFoundList();
                System.out.println(queue.getQueueId()+"\t"+exts.size());
                for (MessageExt ext :exts){
                    System.out.println(ext.getTopic()+"\t"+ext.getQueueId()+"\t"+ext.getMsgId()+"\t"+new String(ext.getBody()));

                }
            }while (true);

        }
        consumer.shutdown();
    }
}
