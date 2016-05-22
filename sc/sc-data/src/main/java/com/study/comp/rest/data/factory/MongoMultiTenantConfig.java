package com.study.comp.rest.data.factory;

import java.net.UnknownHostException;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;

@Configuration
public class MongoMultiTenantConfig {

	@Resource
	public Environment env;

	@Bean
	public MongoTemplate mongoTemplate(final Mongo mongo) throws Exception {
		return new MongoTemplate(mongoDbFactory(mongo));
	}

	@Bean
	public MultiTenantMongoDbFactory mongoDbFactory(final Mongo mongo) throws Exception {
		return new MultiTenantMongoDbFactory(mongo(), MultiTenantMongoDbFactory.defaultName);
	}

	public @Bean MongoClient mongo() throws UnknownHostException {
		MongoClientOptions options = MongoClientOptions.builder().connectionsPerHost(10).connectTimeout(4000).build();
		MongoClient client = new MongoClient(env.getProperty("sa.mongo.host"), options);
		return client;
	}

}
