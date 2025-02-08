package com.example.FileService.command.controllers;

import com.example.FileService.command.models.FileUploadCommand;
import com.example.FileService.command.services.FileCommandHandler;
import com.example.FileService.command.models.FileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/command/files")
public class FileCommandController {

    @Autowired
    private FileCommandHandler fileService;

    // Endpoint to upload a file (Create)
    @PostMapping
    public ResponseEntity<FileEntity> uploadFile(@RequestBody FileUploadCommand command) {
        FileEntity file = fileService.handleFileUpload(command);
        return new ResponseEntity<>(file, HttpStatus.CREATED);
    }

    // Endpoint to update a file (Update)
    @PutMapping("/{fileId}")
    public ResponseEntity<FileEntity> updateFile(@PathVariable String fileId, @RequestBody FileUploadCommand command) {
        FileEntity file = fileService.updateFile(fileId, command.getFileName(), command.getSize(), command.getFileType());
        if (file == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(file, HttpStatus.OK);
    }

    // Endpoint to delete a file (Delete)
    @DeleteMapping("/{fileId}")
    public ResponseEntity<Void> deleteFile(@PathVariable String fileId) {
        boolean deleted = fileService.deleteFile(fileId);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
