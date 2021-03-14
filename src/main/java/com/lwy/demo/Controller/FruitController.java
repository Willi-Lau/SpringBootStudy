package com.lwy.demo.Controller;


import com.lwy.demo.entity.Fruit;
import com.lwy.demo.service.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/FruitController")
@RestController
public class FruitController {
    @Autowired
    private FruitService service;

    /**
     * @Cacheable 调用方法前执行，对方法返回的结果进行缓存，从缓存中查询时候方法都不会进，只有第一次才会进方法
     * @param id
     * @return
     */
    @GetMapping("/fruit/{id}")
    @Cacheable(cacheNames = {"fruit1","fruit_info"},key = "#id")   //##result.id 作用是取出返回的结果里的id的值
    public Fruit test1(@PathVariable String id){

        Fruit fruit = service.selectFruitByid(id);
        System.out.println(fruit.toString());
        return fruit;
    }

    /**
     * @CachePut 先调用方法，再把方法的返回结果放进缓存中
     * 注意这里的key 需要从返回的结果里获取 不然会报错
     * 注意 更新数据 cacahename 和 key 必须和存入时@Cacheable 的一模一样
     */
    @GetMapping("/updatefruit/{id}")
    @CachePut(cacheNames = {"fruit1","fruit_info"},key = "#result.id")   //##result.id 作用是取出返回的结果里的id的值
    public Fruit test2(@PathVariable String id){

        service.updateFruitByid(id);
        //再次查询 更新缓存
        Fruit fruit = service.selectFruitByid(id);
        return fruit;
    }


}
