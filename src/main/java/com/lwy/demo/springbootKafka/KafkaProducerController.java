package com.lwy.demo.springbootKafka;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.err;
import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@RequestMapping
@RestController
public class KafkaProducerController {

    // Kafka 模板用来向 kafka 发送数据
    @Autowired
    KafkaTemplate<String, String> kafka;

    @RequestMapping("/atguigu")
    public  String data(String msg) throws ExecutionException, InterruptedException {
        //发送
        kafka.send("first", msg).addCallback(success ->{
            //成功业务逻辑
            if(success != null){
                System.out.println("success send kafka" + success.getRecordMetadata().toString());
            }

        },failure->{
            //失败业务逻辑
            System.out.println("error send kafka" + failure.getMessage());
        });
        return "ok";
    }
}
