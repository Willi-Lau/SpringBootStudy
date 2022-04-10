package com.lwy.demo.activemq;


import com.lwy.demo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.TextMessage;

@Component
public class Queue_Consumer {

    @JmsListener(destination = "${myqueue}")   //通过注解检监听 选择监听的对象 启动也是通过主启动类
    public void receive(Student s) throws Exception{   //TextMessage textMessage   textMessage.getText()
        System.out.println("****** 消费者收到消息"+s.toString());
    }
}
