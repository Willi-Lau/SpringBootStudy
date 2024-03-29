package com.lwy.demo.springdataElasticSearch;

import lombok.Data;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

import javax.naming.ldap.PagedResultsControl;

/**
 * es 核心类
 */
//@Configuration
//@ConfigurationProperties(prefix = "elasticsearch")   //关联 application.yaml
//@Data
public class ElasticSearchConfig extends AbstractElasticsearchConfiguration {

    private String host;
    private Integer port;

    @Override
    public RestHighLevelClient elasticsearchClient() {
        RestClientBuilder builder = RestClient.builder(new HttpHost(host,port));
        RestHighLevelClient restHighLevelClient =  new RestHighLevelClient(builder);
        return restHighLevelClient;
    }
}
