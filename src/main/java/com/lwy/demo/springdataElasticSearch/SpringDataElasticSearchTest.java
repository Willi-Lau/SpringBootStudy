package com.lwy.demo.springdataElasticSearch;

import com.lwy.demo.entity.User;
import org.elasticsearch.index.query.FuzzyQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * @author Administrator
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataElasticSearchTest {

    //模板
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    /**
     * 创建 index
     */
    @Test
    public void createIndex(){
        System.out.println("创建成功");
    }

    /**
     * 删除index
     */
    @Test
    public void deleteIndex(){
        boolean b = elasticsearchRestTemplate.deleteIndex(User.class);
        System.out.println(b);
    }

    @Autowired
    private UserDao userDao;

    /**
     * 新增 document
     */
    @Test
    public void save(){
        User user = new User();
        //添加对象
        User userBuilder = User.builder().age(10).name("雷军1").sex("男").build();
        userDao.save(userBuilder);
    }

    /**
     * 修改 document
     */
    @Test
    public void update(){
        User user = new User();
        //添加对象
        User userBuilder = User.builder().age(20).name("雷军").sex("男").build();
        userDao.save(userBuilder);
    }

    /**
     * 查询 document 根据指定id
     */
    @Test
    public void findById(){
        User user = userDao.findById("雷军").get();
        System.out.println(user);
    }

    /**
     * 查询 document 查询所有
     */
    @Test
    public void findAll(){
        Iterable<User> userDaoAll = userDao.findAll();
        userDaoAll.forEach(System.out::println);
    }

    /**
     * 删除 document 根据对象
     */
    @Test
    public void delete(){
        User userBuilder = User.builder().age(20).name("雷军").sex("男").build();
        userDao.delete(userBuilder);
    }

    /**
     * 批量新增 document
     */
    @Test
    public void saveAll(){
        User user = new User();
        //添加对象
        User userBuilder1 = User.builder().age(10).name("雷军1").sex("男").build();
        User userBuilder2 = User.builder().age(20).name("雷军2").sex("男").build();
        User userBuilder3 = User.builder().age(30).name("雷军3").sex("男").build();
        ArrayList<User> users = new ArrayList<>();
        Collections.addAll(users,userBuilder1,userBuilder2,userBuilder3);
        userDao.saveAll(users);
    }

    /**
     * 分页查询 document
     */
    @Test
    public void findByPageable(){
        //设置排序方式 根据什么排序
        Sort sort = Sort.by(Sort.Direction.DESC, "age");
        //每一页多少条
        int pageSize = 5;
        //当前页 从0开始
        int pageCount = 0;
        //设置分页条件
        PageRequest pageRequest = PageRequest.of(pageCount, pageSize, sort);
        //分页查询
        Page<User> users = userDao.findAll(pageRequest);
        users.forEach(System.out::println);
    }

    /**
     * 条件查询 document + 分页
     */
    @Test
    public void termQuery(){
        //条件
            TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "雷军");
           // FuzzyQueryBuilder termQueryBuilder = QueryBuilders.fuzzyQuery("name","雷军");
        //分页
            //设置排序方式 根据什么排序
            Sort sort = Sort.by(Sort.Direction.DESC, "age");
            //每一页多少条
            int pageSize = 5;
            //当前页 从0开始
            int pageCount = 0;
            //设置分页条件
            PageRequest pageRequest = PageRequest.of(pageCount, pageSize, sort);
        Iterable<User> users = userDao.search(termQueryBuilder,pageRequest);
        users.forEach(System.out::println);
    }
}
