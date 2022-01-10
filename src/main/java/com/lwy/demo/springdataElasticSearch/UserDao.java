package com.lwy.demo.springdataElasticSearch;

import com.lwy.demo.entity.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;


/**
 * @author Administrator
 * 对应 entity / User
 * <User/String><类型/主键类型>
 */
@Repository
public interface UserDao extends ElasticsearchRepository<User,String> {
}
