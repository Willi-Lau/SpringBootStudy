package com.lwy.demo.service;

import com.lwy.demo.entity.Fruit;
import org.apache.ibatis.annotations.Select;

public interface FruitService {

    Fruit selectFruitByid(String id);

    void updateFruitByid(String id);
}
