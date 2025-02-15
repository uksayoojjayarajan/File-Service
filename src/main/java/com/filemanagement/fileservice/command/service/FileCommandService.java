package com.filemanagement.fileservice.command.service;

import com.filemanagement.fileservice.command.commands.DeleteFileCommand;
import com.filemanagement.fileservice.command.commands.UpdateFileMetadataCommand;
import com.filemanagement.fileservice.command.commands.UploadFileCommand;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.CompletableFuture;

public interface FileCommandService {


    public CompletableFuture<String> uploadFile(UploadFileCommand command, MultipartFile file);

    public CompletableFuture<String> deleteFile(DeleteFileCommand command) ;

    public CompletableFuture<String> updateFileMetadata(UpdateFileMetadataCommand command) ;

}
