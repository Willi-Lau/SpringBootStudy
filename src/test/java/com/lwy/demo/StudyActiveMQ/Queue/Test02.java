package com.lwy.demo.StudyActiveMQ.Queue;

import lombok.Data;

import javax.annotation.sql.DataSourceDefinition;
public class Test02 {
    public static void main(String[] args) {
        Test01 m = new sub();
        //m.printName();
    }
}
@Data
class Test01 {
    public String basename = "base";
    public Test01(){
        printName();
    }
    public void printName(){
        System.out.println(basename);
    }
}
@Data
class sub extends Test01{
     static String basename = "sub";
    static String abc = "hahahah";
    public void printName(){
        System.out.println(basename);
        System.out.println(abc);
    }
}