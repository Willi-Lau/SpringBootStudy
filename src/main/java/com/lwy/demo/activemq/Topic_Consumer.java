package com.lwy.demo.activemq;


import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.TextMessage;

@Component
public class Topic_Consumer {

    @JmsListener(destination = "${mytopic}")   //通过注解检监听 选择监听的对象 启动也是通过主启动类
    public void receive(TextMessage textMessage) throws Exception{
        System.out.println("****** 消费者收到消息"+textMessage.getText());
    }
}
