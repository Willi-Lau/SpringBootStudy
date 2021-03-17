package com.lwy.demo.activemq;


import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import javax.jms.Topic;


@Configuration
@EnableJms
public class ConfigBeanActiveMQ{
    /**
     * 注入Queue名字
     */
    @Value("${mytopic}")
    private String myQueue;
    /**
     * 创建返回Queue
     * @return
     */
    @Bean
    public Queue queue(){
            return new ActiveMQQueue(myQueue);
    }
    /**
     * 注入Topic 名字
     */
    @Value("${mytopic}")
    private String topicname;
    /**
     * 创建主题
     */
    @Bean
    public Topic topic(){
        return new ActiveMQTopic(topicname);
    }
}
