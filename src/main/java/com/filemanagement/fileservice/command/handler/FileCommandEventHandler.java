package com.filemanagement.fileservice.command.handler;

import com.filemanagement.fileservice.command.event.FileMetadataUpdateEvent;
import com.filemanagement.fileservice.command.event.FileUploadEvent;
import com.filemanagement.fileservice.data.model.File;
import com.filemanagement.fileservice.command.event.*;

import com.filemanagement.fileservice.data.mongo.repository.FileCommandRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileCommandEventHandler {

    private final FileCommandRepository fileRepository;

    @EventHandler
    public void on(FileUploadEvent event) {
        File file = new File();
        file.setFileId(event.getFileId());
        file.setFileName(event.getFileName());
        file.setFileType(event.getFileType());
        file.setFileSize(event.getFileSize());
        file.setUserId(event.getUserId());
        file.setFileLocation(event.getFileUrl());
        file.setDateCreated(event.getDateCreated());
        file.setDateUpdated(event.getDateUpdated());

        fileRepository.save(file);
    }

    @EventHandler
    public void on(FileMetadataUpdateEvent event) {
        Optional<File> optionalFile = fileRepository.findById(event.getFileId());
        if (optionalFile.isPresent()) {
            File file = optionalFile.get();
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

