package demo.producer;


import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;


/**
 * 发送同步消息
 */
public class SyncProducer {
    public static void main(String[] args) throws Exception {
        // 初始化一个producer并设置Producer group name
        DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name"); //（1）
        // 设置NameServer地址
        producer.setNamesrvAddr("172.16.147.128:9876");  //（2）
        // 启动producer
        producer.start();
        for (int i = 0; true; i++) {
            // 创建一条消息，并指定topic、tag、body等信息，tag可以理解成标签，对消息进行再归类，RocketMQ可以在消费端对tag进行过滤
            Message msg = new Message("TopicTest" /* Topic */,
                    "TagA" /* Tag */,
                    ("Hello RocketMQ " + i).getBytes() /* Message body */
            );   //（3）
            // 利用producer进行发送，并同步等待发送结果
            SendResult sendResult = producer.send(msg);   //（4）
            System.out.printf("%s%n", sendResult);
        }

    }

}
