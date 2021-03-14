package com.lwy.demo;

import com.lwy.demo.entity.Student;
import com.lwy.demo.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;


/**
 * 测试Redis
 */
@SpringBootTest
class DemoApplicationTests {

    @Autowired
    StringRedisTemplate stringRedisTemplate;   //操作字符串

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedisUtil redisUtil;



    @Test
    void contextLoads() {
        //设置
        stringRedisTemplate.opsForValue().set("springboot1","20210310");
        //读取
        stringRedisTemplate.opsForValue().get("springboot1");

    }


    @Test
    void test02(){
        //测试保存对象
        Student student = new Student();
        student.setAge(100);
        student.setName("李四");
        student.setSchool("LNGD");
        //System.out.println(redisTemplate);
        //redisUtil.set(student.getName(),student);
        Object o = redisUtil.get("fruit1::90001");
        System.out.println(o);

        // 将数据以json形式保存
    }

}
