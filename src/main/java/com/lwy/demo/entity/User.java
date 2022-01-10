package com.lwy.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author Administrator
 * es 配置在这里
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Document(indexName = "user_hello",shards = 3,replicas = 1)   //es索引名字user 分片3 副本1
public class User {
    @Id //主键
    @Field(type = FieldType.Text)   //分词
    private String name;
    @Field(type = FieldType.Keyword)  //关键字 不分词
    private String sex;
    @Field(type = FieldType.Keyword)
    private Integer age;
}
