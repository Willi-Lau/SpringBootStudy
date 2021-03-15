package com.lwy.demo.StudyActiveMQ.Topic;


import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.jupiter.api.Test;

import javax.jms.*;
import java.util.HashMap;

/**
 * 消息生产者
 */
public class Producer {
    //连接位置 根据主机ip地址  查看IP  ipconfig
    public static final String ACTIVEMQ_URL = "tcp://127.0.0.1:61616";
    public static final String TOPIC_NAME = "topic01";

    @Test
    void test0() throws Exception{

        //创建连接工厂
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //通过连接工厂获得连接Connection
        Connection connection = factory.createConnection();
        //创建会话 session  参数 事务/接收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建目的地 具体是队列还是主题
         Topic topic = session.createTopic(TOPIC_NAME);
        System.out.println("我是一号生产者");
        //创建消息的生产者
        MessageProducer producer = session.createProducer(topic);

        //持久化
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        //启动访问
        connection.start();

        //通过使用消息生产者生产三条消息到MQ队列里
        for (int i=0;i<3;i++)
        {
            //创建消息 普通类型   留言卡
            TextMessage textMessage = session.createTextMessage("Topicname" + Integer.toString(i + 1));//一个字符串
            //设置消息文本信息
            textMessage.setText("哈哈哈哈哈哈哈哈");
            //设置消息属性 去重功能
            textMessage.setStringProperty("type","boss");
            //发送到MQ
            producer.send(textMessage);



            //创建Map类型数据
            MapMessage mapMessage = session.createMapMessage();
            //设置String类型 kv
            mapMessage.setString("key1","   map   value1");
            //发送到MQ
            producer.send(mapMessage);

        }
        //释放资源
        producer.close();
        session.close();
        connection.close();
        System.out.println("队列消息发送到MQ完成");


    }
}
