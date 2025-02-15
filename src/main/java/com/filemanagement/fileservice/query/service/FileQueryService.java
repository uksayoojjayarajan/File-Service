package com.filemanagement.fileservice.query.service;

import com.filemanagement.fileservice.data.model.FileProjection;

import java.util.List;

public interface FileQueryService {


    public FileProjection getFile(String fileId) ;

    public List<FileProjection> searchFiles(String query);

    public List<FileProjection> getAllFiles();
}
