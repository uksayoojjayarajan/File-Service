package com.filemanagement.fileservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.filemanagement.fileservice.data.repository.mongo")
public class MongoConfig {
    // MongoDB is auto-configured by Spring Boot, no need for additional beans
}

