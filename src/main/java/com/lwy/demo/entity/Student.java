package com.lwy.demo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Student implements Serializable {
     String name;
     int age;
     String school;
}
