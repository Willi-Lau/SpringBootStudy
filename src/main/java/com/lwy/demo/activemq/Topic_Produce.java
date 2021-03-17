package com.lwy.demo.activemq;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Topic;

@Component
public class Topic_Produce {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Topic topic;
    /**
     * 执行普通发送
     */
    public void produceTopic(){
        jmsMessagingTemplate.convertAndSend(topic,"发送主题：哈哈哈哈哈哈哈哈哈哈哈哈哈");
    }
    /**
     * 执行定时调用
     */
    @Scheduled(fixedDelay = 3000L)
    public void produceTopicScheduled(){
        jmsMessagingTemplate.convertAndSend(topic,"定时发送主题：哈哈哈哈哈哈哈哈哈哈哈哈哈");
    }

}
