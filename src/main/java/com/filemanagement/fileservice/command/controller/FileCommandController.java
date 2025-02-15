package com.filemanagement.fileservice.command.controller;

import com.filemanagement.fileservice.command.commands.DeleteFileCommand;
import com.filemanagement.fileservice.command.commands.UploadFileCommand;
import com.filemanagement.fileservice.command.commands.UpdateFileMetadataCommand;
import com.filemanagement.fileservice.command.service.FileCommandService;
import com.filemanagement.fileservice.command.service.FileCommandServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileCommandController {

    private final FileCommandService fileCommandService;

    @PostMapping("/v1/add")
    public ResponseEntity<String> fileUpload(@RequestParam("file") MultipartFile file, @RequestParam("userId") String userId) {
        try {


            if(file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File Required");
            }
            String fileId = UUID.randomUUID().toString();
            UploadFileCommand uploadFileCommand = UploadFileCommand.builder()
                    .fileId(fileId)
                    .fileName(file.getOriginalFilename())
                    .fileType(file.getContentType())
                    .fileSize(file.getSize())
                    .userId(userId)
                    .fileUrl("")
                    .dateCreated(LocalDate.now())
                    .dateUpdated(LocalDate.now())
                    .build();

            fileCommandService.uploadFile(uploadFileCommand, file);
            return ResponseEntity.ok(fileId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
        }
    }

    @PutMapping("/v1/update/{fileId}")
    public CompletableFuture<ResponseEntity<String>> updateFileMetadata(
            @PathVariable String fileId,
            @RequestParam String fileName,
            @RequestParam String fileType) {

        UpdateFileMetadataCommand command = new UpdateFileMetadataCommand(fileId, fileName, fileType, LocalDate.now());
        return fileCommandService.updateFileMetadata(command)
                .thenApply(result -> ResponseEntity.ok("File metadata updated successfully"));
    }

    @DeleteMapping("/v1/delete/{fileId}")
    public CompletableFuture<ResponseEntity<String>> fileDelete(@PathVariable String fileId) {
        DeleteFileCommand deleteFileCommand = new DeleteFileCommand(fileId);
        return fileCommandService.deleteFile(deleteFileCommand)
                .thenApply(result -> ResponseEntity.ok("Deleted the file"));
    }
}
