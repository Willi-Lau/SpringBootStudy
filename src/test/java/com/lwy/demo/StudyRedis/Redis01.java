package com.lwy.demo.StudyRedis;

import redis.clients.jedis.Jedis;

import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

public class Redis01 {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.40.129",6379);
        jedis.auth("12345");
        jedis.select(0);
   


//        String phone = "18240394122";
//        jedis.setex("Phone"+phone,60*60*24,randomNum());
//        jedis.setnx("Phone"+phone+"count", "0");

        for (int i=0;i<20000;i++){
            jedis.set(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        }


        jedis.close();




    }

    public static String randomNum(){
        String randomNum = "";
        StringBuilder stringBuilder = new StringBuilder(randomNum);
        for (int i=0;i<6;i++){
            Random r = new Random();
            int ints = r.nextInt(9);

            stringBuilder.append(Integer.toString(ints));
        }
        return stringBuilder.toString();
    }
}
