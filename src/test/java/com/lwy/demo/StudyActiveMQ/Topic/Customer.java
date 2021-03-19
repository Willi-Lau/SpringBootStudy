package com.lwy.demo.StudyActiveMQ.Topic;

import lombok.SneakyThrows;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Customer {

    //连接位置 根据主机ip地址  查看IP  ipconfig
    public static final String ACTIVEMQ_URL = "tcp://127.0.0.1:61616";
    public static final String TOPIC_NAME = "topic-jdbc";
    public static void main(String[] args) throws Exception{
        /**
         * 消费者 获取MQ的地址和名字要和生产者一样
         */
        //创建连接工厂
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //通过连接工厂获得连接Connection
        Connection connection = factory.createConnection();
        //启动访问
        connection.start();
        //创建会话 session  参数 事务/接收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建目的地 具体是队列还是主题
        Topic topic = session.createTopic(TOPIC_NAME);
        //创建消息消费者
        MessageConsumer consumer = session.createConsumer(topic);

        /**
         * 通过监听器监听 有消息消费
         */
        consumer.setMessageListener(new MessageListener() {
            //随时监听 只有有小细腰处理了才打开处理
            @SneakyThrows
            @Override
            public void onMessage(Message message) {
                if(null != message && message instanceof TextMessage){
                    //接收信息 text类型
                    TextMessage textMessage = (TextMessage) message;
                    //获取接收的消息
                    System.out.println("消费者1接收到消息 text" + textMessage.getText());
                    //接收消息属性
                    System.out.println("消费者1接收到消息 text 消息属性 "+textMessage.getStringProperty("type"));

                }
                    /**
                     * 监听map
                     */
                if(null != message && message instanceof MapMessage){
                    //接收信息 text类型
                    MapMessage mapMessage = (MapMessage) message;
                    //获取接收的消息
                    System.out.println("消费者1接收到消息 map" + mapMessage.getString("key1"));

                }
            }
        });



        //保证不饿能错过信息
        System.in.read();

        //关闭资源
        consumer.close();
        session.close();
        connection.close();

        /**
         *消息队列生产者消费者相关问题：topic模式
         *
         *
         *             启动 01 02两个消费者 然后再让生产者01生产6条消息
         *                 ->   消费者01 得到6条消息
         *                      消费者02 得到6条消息   订阅主题所有消息都能得到
         *
         *
         */



    }
}
