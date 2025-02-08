package com.example.FileService.command.repositories;

import com.example.FileService.command.models.FileEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends MongoRepository<FileEntity, String> {
    // You can add custom query methods here, if needed.
    // For example, find a file by its file name:
    // Optional<FileEntity> findByFileName(String fileName);
}
