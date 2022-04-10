package com.lwy.demo.springbootKafka;

import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.Arrays;
import java.util.HashMap;

@Configuration
public class KafkaConsumer {
    // 指定要监听的 topic
    @KafkaListener(topics = "first")
    public void consumeTopic(String msg) { // 参数: 收到的 value
        System.out.println("收到的信息: " + msg);
    }
}
