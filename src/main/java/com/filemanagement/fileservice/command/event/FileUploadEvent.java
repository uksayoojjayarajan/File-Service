package com.filemanagement.fileservice.command.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileUploadEvent {
    private String fileId;
    private String fileName;
    private String fileType;
    private Long fileSize;
    private String userId;
    private String fileUrl;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}
