package com.filemanagement.fileservice.command.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateFileMetadataCommand {
    @TargetAggregateIdentifier
    private String fileId;
    private String fileName;
    private String fileType;
    private LocalDate dateUpdated;
}
