package com.lwy.demo.entity;


import lombok.Data;

import java.io.Serializable;

@Data
public class Fruit implements Serializable
{
    private String id;
    private String name;
    private int inprice;
    private int outprice;
    private int num;
    private String util;



}
