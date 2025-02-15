package com.filemanagement.fileservice.command.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UploadFileCommand {
    @TargetAggregateIdentifier
    private String fileId;
    private String fileName;
    private String fileType;
    private Long fileSize;
    private String userId;
    private String fileUrl; // S3 URL
    private LocalDate dateCreated;
    private LocalDate dateUpdated;
}
