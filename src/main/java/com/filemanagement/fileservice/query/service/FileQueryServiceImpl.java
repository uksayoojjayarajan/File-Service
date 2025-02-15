package com.filemanagement.fileservice.query.service;

import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.filemanagement.fileservice.data.model.FileProjection;
import com.filemanagement.fileservice.data.repository.FileQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileQueryServiceImpl implements FileQueryService {
    private final FileQueryRepository fileQueryRepository;

    public FileProjection getFile(String fileId) {
        return fileQueryRepository.findById(fileId).orElse(null);
    }

    public List<FileProjection> searchFiles(String query) {
        return fileQueryRepository.findByFileNameContaining(query);
    }

    @Override
    public List<FileProjection> getAllFiles() {
        return (List<FileProjection>) fileQueryRepository.findAll(Sort.by(Sort.Direction.DESC,"fileId"));
    }


}
