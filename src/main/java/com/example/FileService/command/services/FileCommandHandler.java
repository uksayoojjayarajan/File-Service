package com.example.FileService.command.services;

import com.example.FileService.command.models.FileUploadCommand;
import com.example.FileService.command.repositories.FileRepository;
import com.example.FileService.command.events.FileUploadedEvent;
import com.example.FileService.command.handlers.EventPublisher;
import com.example.FileService.command.models.FileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class FileCommandHandler {

    @Autowired
    private FileRepository fileRepository; // MongoDB repository

    @Autowired
    private EventPublisher eventPublisher;

    public FileEntity handleFileUpload(FileUploadCommand command) {
        // Save file metadata into MongoDB (Command side)
        FileEntity file = new FileEntity(command.getFileId(), command.getFileName(), command.getSize(), command.getFileType(), LocalDateTime.now());
        fileRepository.save(file);

        // Emit an event to notify other microservices
//        eventPublisher.publish(new FileUploadedEvent(command.getFileId(), command.getFileName()));
        return file;
    }



    // Update file (update)
    public FileEntity updateFile(String fileId, String fileName, long size, String fileType) {
        Optional<FileEntity> existingFile = fileRepository.findById(fileId);
        if (existingFile.isPresent()) {
            FileEntity file = existingFile.get();
            file.setFileName(fileName);
            file.setSize(size);
            file.setFileType(fileType);
            return fileRepository.save(file);
        }
        return null;
    }

    // Delete file (delete)
    public boolean deleteFile(String fileId) {
        Optional<FileEntity> existingFile = fileRepository.findById(fileId);
        if (existingFile.isPresent()) {
            fileRepository.deleteById(fileId);
            return true;
        }
        return false;
    }
}
