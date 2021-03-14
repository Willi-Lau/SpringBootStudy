package com.lwy.demo.service.impl;

import com.lwy.demo.entity.Fruit;
import com.lwy.demo.mapper.FruitMapper;
import com.lwy.demo.service.FruitService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class FruitServiceimpl implements FruitService {

    @Autowired
    private FruitMapper fruitMapper;

    @Override
    public Fruit selectFruitByid(String id) {
        return fruitMapper.selectFruitByid(id);
    }

    @Override
    public void updateFruitByid(String id) {
        fruitMapper.updateFruitByid(id);
    }
}
