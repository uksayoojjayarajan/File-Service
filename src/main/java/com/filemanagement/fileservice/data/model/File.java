package com.filemanagement.fileservice.data.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Document(collection = "file")
public class File {
    @Id
    private String fileId;
    @NotBlank(message = "File name cannot be empty")
    private String fileName;
    @NotNull
    @Lob
    @Size(max = 200 * 1024 * 1024, message = "File size must not exceed 200MB")
    private byte[] fileContent;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
    private String fileType;
    private Long fileSize;
    private String fileLocation;
    private String userId;
}
