package com.lwy.demo.mapper;

import com.lwy.demo.entity.Fruit;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface FruitMapper {

    @Select("select * from fruit_information where id = #{id}")
    Fruit selectFruitByid(String id);

    @Update("update fruit_information set num = 100 where id = #{id}")
    void updateFruitByid(String id);
}
