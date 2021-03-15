package com.lwy.demo.StudyActiveMQ.Queue_Transation;

import lombok.SneakyThrows;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消息消费者 开启事务
 */
public class Customer {
    //连接位置 根据主机ip地址  查看IP  ipconfig
    public static final String ACTIVEMQ_URL = "tcp://127.0.0.1:61616";
    public static final String QUEUE_NAME = "queue01_tx";
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
        //创建会话 session  参数 事务/接收   true 为开启事务
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        //创建目的地 具体是队列还是主题
        Queue queue = session.createQueue(QUEUE_NAME);

        //创建消息消费者
        MessageConsumer consumer = session.createConsumer(queue);

        /**
         * 通过监听器监听 有消息消费
         */
        while (true){
                //这里消费者接收的类型必须和生产者发送的类型一样所以要进行类型转换
                //如果 .receice() 没有参数就会一直霸占资源 应该设置时间
                TextMessage textMessage = (TextMessage)consumer.receive(4000L);
                //消息不是空再开始
                if(textMessage != null){
                    //获取消息的信息
                    System.out.println("消费者1接收到消息" + textMessage.getText());
                }
                else {
                    //跳出循环
                    break;
                }
            }



        //关闭资源
        consumer.close();
        //开启事务需要提交
        session.commit();
        session.close();
        connection.close();

        /**
         *消息队列生产者消费者相关问题：queue模式
         *             1.生产者01先生产， 消费者01再消费
         *                 ->   消费者01 得到消息
         *
         *             2.生产者01先生产， 然后先启动消费者01 再启动消费者02
         *                 ->    消费者01 得到消息
         *                       消费者02 无消息
         *
         *             3.启动 01 02两个消费者 然后再让生产者01生产6条消息
         *                 ->   消费者01 得到3条消息
         *                      消费者02 得到3条消息   平均分配
         */



    }
}
