package com.filemanagement.fileservice.data.repository;


import com.filemanagement.fileservice.data.model.File;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileCommandRepository extends MongoRepository<File, String> {
}



