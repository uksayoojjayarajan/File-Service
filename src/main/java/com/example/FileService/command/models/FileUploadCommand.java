package com.example.FileService.command.models;

import lombok.*;

@Getter()
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadCommand {
    private String fileId;
    private String fileName;
    private long size;
    private String fileType;

}
