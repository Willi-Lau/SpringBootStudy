package com.lwy.demo.activemq;


import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

@Component
public class Queue_Produce {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    /**
     * 调用Config里的queue
     */
    @Autowired
    private Queue queue;

    /**
     * 普通发送方法   点击发消息 每次需要调用这个方法
     */
    public void produceMsg(){
        //执行发送
        jmsMessagingTemplate.convertAndSend(queue,"哈哈哈哈哈哈哈");
    }
    /**
     * 每隔三秒发送一次消息   定时发送的测试需要通过主启动类来测试
     */
    @Scheduled(fixedDelay = 3000L)   //注解作用  括号里设置时间 每三秒自动发送
    public void produceMsgScheduled(){
        jmsMessagingTemplate.convertAndSend(queue,"定时投放哈哈哈哈哈哈哈");
    }

}
