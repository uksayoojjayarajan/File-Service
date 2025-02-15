package com.filemanagement.fileservice.command.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileMetadataUpdateEvent {
    private String fileId;
    private String fileName;
    private String fileType;
    private LocalDate dateUpdated;
}
