package com.filemanagement.fileservice.command.handler;

import com.filemanagement.fileservice.command.event.FileMetadataUpdateEvent;
import com.filemanagement.fileservice.command.event.FileUploadEvent;
import com.filemanagement.fileservice.data.FileEntity;
import com.filemanagement.fileservice.data.FileRepository;
import com.filemanagement.fileservice.event.FileUploadedEvent;
import com.filemanagement.fileservice.event.FileMetadataUpdatedEvent;
import com.filemanagement.fileservice.event.FileDeletedEvent;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileCommandEventHandler {

    private final FileRepository fileRepository;

    @EventHandler
    public void on(FileUploadEvent event) {
        FileEntity file = new FileEntity();
        file.setFileId(event.getFileId());
        file.setFileName(event.getFileName());
        file.setFileType(event.getFileType());
        file.setFileSize(event.getFileSize());
        file.setUserId(event.getUserId());
        file.setFileUrl(event.getFileUrl());
        file.setDateCreated(event.getDateCreated());
        file.setDateUpdated(event.getDateUpdated());

        fileRepository.save(file);
    }

    @EventHandler
    public void on(FileMetadataUpdateEvent event) {
        Optional<FileEntity> optionalFile = fileRepository.findById(event.getFileId());
        if (optionalFile.isPresent()) {
            FileEntity file = optionalFile.get();
            file.setFileName(event.getFileName());
            file.setFileType(event.getFileType());
            file.setDateUpdated(event.getDateUpdated());

            fileRepository.save(file);
        }
    }

    @EventHandler
    public void on(FileDeleteEvent event) {
        fileRepository.deleteById(event.getFileId());
    }
}

