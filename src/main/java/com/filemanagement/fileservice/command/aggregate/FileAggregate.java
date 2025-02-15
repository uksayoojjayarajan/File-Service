package com.filemanagement.fileservice.command.aggregate;

import com.filemanagement.fileservice.command.commands.DeleteFileCommand;
import com.filemanagement.fileservice.command.commands.UpdateFileMetadataCommand;
import com.filemanagement.fileservice.command.commands.UploadFileCommand;
import com.filemanagement.fileservice.command.event.FileDeleteEvent;
import com.filemanagement.fileservice.command.event.FileMetadataUpdateEvent;
import com.filemanagement.fileservice.command.event.FileUploadEvent;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.LocalDateTime;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Data
@NoArgsConstructor
public class FileAggregate {
    @AggregateIdentifier
    private String fileId;
    private String fileName;
    private String fileType;
    private Long fileSize;
    private String userId;
    private String fileUrl;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;

    @CommandHandler
    public FileAggregate(UploadFileCommand command) {
        apply(new FileUploadEvent(command.getFileId(), command.getFileName(), command.getFileType(), command.getFileSize(), command.getUserId(), command.getFileUrl(), command.getDateCreated(), command.getDateUpdated()));
    }

    @EventSourcingHandler
    public void on(FileUploadEvent event) {
        this.fileId = event.getFileId();
        this.fileName = event.getFileName();
        this.fileType = event.getFileType();
        this.fileSize = event.getFileSize();
        this.userId = event.getUserId();
        this.fileUrl = event.getFileUrl();
        this.dateCreated = event.getDateCreated();
        this.dateUpdated = event.getDateUpdated();
    }

    @CommandHandler
    public void handle(UpdateFileMetadataCommand command) {
        apply(new FileMetadataUpdateEvent(command.getFileId(), command.getFileName(), command.getFileType(), command.getDateUpdated()));
    }

    @EventSourcingHandler
    public void on(FileMetadataUpdateEvent event) {
        this.fileName = event.getFileName();
        this.fileType = event.getFileType();
        this.dateUpdated = event.getDateUpdated();
    }

    @CommandHandler
    public void handle(DeleteFileCommand command) {
        apply(new FileDeleteEvent(command.getFileId()));
    }

    @EventSourcingHandler
    public void on(FileDeleteEvent event) {
        // Aggregate removed
    }
}
