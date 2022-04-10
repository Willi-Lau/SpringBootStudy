package com.lwy.demo.StudyActiveMQ.springboot_mq;


import com.lwy.demo.activemq.Queue_Consumer;
import com.lwy.demo.activemq.Queue_Produce;
import com.lwy.demo.entity.Student;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import javax.jms.TextMessage;

/**
 * 在这里测试springBoot 消息发送
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TestActiveMQ {

    @Resource
    private Queue_Produce queue_produce;

    @Resource
    private Queue_Consumer queue_consumer;

    /**
     * 普通发送消息
     * @throws Exception
     */
    @Test
    public void testsend() throws Exception{
        Student s = new Student();
        s.setName("张三");
        //执行发送
        queue_produce.produceMsg(s);


    }

}
