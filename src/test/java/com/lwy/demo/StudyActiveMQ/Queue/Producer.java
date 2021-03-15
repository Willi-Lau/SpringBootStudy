package com.lwy.demo.StudyActiveMQ.Queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.jupiter.api.Test;

import javax.jms.*;

/**
 * 消息生产者
 */
public class Producer {
    //连接位置 根据主机ip地址  查看IP  ipconfig
    public static final String ACTIVEMQ_URL = "tcp://127.0.0.1:61616";
    public static final String QUEUE_NAME = "queue01";

    @Test
    void test0() throws Exception{

        //创建连接工厂
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //通过连接工厂获得连接Connection
        Connection connection = factory.createConnection();
        //启动访问
        connection.start();
        //创建会话 session  参数 事务/接收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建目的地 具体是队列还是主题
        Queue queue = session.createQueue(QUEUE_NAME);
        //创建消息的生产者
        MessageProducer producer = session.createProducer(queue);

        //设置队列持久化 默认不设置也是持久化
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);

        //通过使用消息生产者生产三条消息到MQ队列里
        for (int i=0;i<3;i++)
        {
            //创建消息   留言卡
            TextMessage textMessage = session.createTextMessage("message  text" + Integer.toString(i + 1));//一个字符串
            //发送到MQ
            producer.send(textMessage);

        }
        //释放资源
        producer.close();
        session.close();
        connection.close();


    }
}
