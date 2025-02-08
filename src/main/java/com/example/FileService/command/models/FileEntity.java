package com.example.FileService.command.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "files")  // This annotation specifies the collection name in MongoDB
public class FileEntity {

    @Id
    private String fileId;
    private String fileName;
    private long size;
    private String fileType;
    private LocalDateTime uploadedAt;


}