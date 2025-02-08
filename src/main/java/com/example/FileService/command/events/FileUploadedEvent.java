package com.example.FileService.command.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FileUploadedEvent {
    private String fileId;
    private String fileName;


  public  FileUploadedEvent(String fileId, String fileName) {
        this.fileId = fileId;
        this.fileName = fileName;
    }


    // Constructor, Getters, and Setters
}
