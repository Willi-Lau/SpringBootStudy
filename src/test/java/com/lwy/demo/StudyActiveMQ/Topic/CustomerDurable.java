package com.lwy.demo.StudyActiveMQ.Topic;


import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 持久化版
 *
 * 需要先启动订阅者再启动生产者
 */

/**
 * 持久化的Topic模式者
 *
 * 1.一定要先运行一次消费者，等于向MQ注册，类似我订阅了这个公众号
 * 2.然后再运行生产者发送消息。此时无论消费者是否在线，都会接受到，如果不在线，下次连接的时候，会把没接收的消息都能接收下来
 */
public class CustomerDurable {

    //连接位置 根据主机ip地址  查看IP  ipconfig
    public static final String ACTIVEMQ_URL = "tcp://127.0.0.1:61616";
    public static final String TOPIC_NAME = "topic01";
    public static void main(String[] args) throws Exception{
        /**
         * 消费者 获取MQ的地址和名字要和生产者一样
         */
        //创建连接工厂
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //通过连接工厂获得连接Connection
        Connection connection = factory.createConnection();
        //设置此订阅者的ID 随便起的
        connection.setClientID("zs");
        //创建会话 session  参数 事务/接收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建目的地 具体是队列还是主题
        Topic topic = session.createTopic(TOPIC_NAME);
        //创建一个持久化的订阅者
        TopicSubscriber topicSubscriber = session.createDurableSubscriber(topic, "备注，，，");
        //启动服务
        connection.start();
        //开启接收信息
        Message message = topicSubscriber.receive();
        //一定要写类型属于 TextMessage 不然会有错
        while (null != message && message instanceof TextMessage){
            TextMessage textMessage = (TextMessage) message;

            System.out.println("收到持久化的Ttopic" + textMessage.getText());
            //有消息就准备跳出
            message  = topicSubscriber.receive(1000L);
        }
        //关闭资源
        session.close();
        connection.close();


}

}
