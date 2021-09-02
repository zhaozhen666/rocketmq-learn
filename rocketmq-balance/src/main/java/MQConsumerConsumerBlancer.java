import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.PullResult;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.Set;

public class MQConsumerConsumerBlancer {
    public static void main(String[] args) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        DefaultMQPullConsumer consumer = new DefaultMQPullConsumer("consumer_balancer_01");

        consumer.setNamesrvAddr("127.0.0.1:9876");

        consumer.start();
        Set<MessageQueue> messageQueues = consumer.fetchSubscribeMessageQueues("mq_balancer");

        for (MessageQueue messageQueue:messageQueues){
            PullResult pullResult = consumer.pull(messageQueue,
                    "*",0,10);
            System.out.println(pullResult);
            for (MessageExt ext :pullResult.getMsgFoundList()){
                System.out.println(ext);
            }
        }

        consumer.shutdown();
    }
}
