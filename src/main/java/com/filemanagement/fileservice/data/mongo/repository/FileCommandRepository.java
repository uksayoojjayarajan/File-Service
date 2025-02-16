package com.filemanagement.fileservice.data.mongo.repository;


import com.filemanagement.fileservice.data.model.File;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileCommandRepository extends MongoRepository<File, String> {
}



