package com.filemanagement.fileservice.command.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileMetadataUpdateEvent {
    private String fileId;
    private String fileName;
    private String fileType;
    private LocalDateTime dateUpdated;
}
