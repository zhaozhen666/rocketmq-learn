import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

public class MQProducerConsumerBalancer {
    public static void main(String[] args) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("producer_tagfilter");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();
        Message message = new Message();
        message.setTopic("mq_balancer");
        message.setBody("hello rmq balancer".getBytes());
        producer.send(message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println(sendResult.getSendStatus());
            }

            @Override
            public void onException(Throwable e) {
                System.out.println(e.getMessage());
            }
        });
        Thread.sleep(3_000);
        producer.shutdown();
    }

}
