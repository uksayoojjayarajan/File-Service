package com.filemanagement.fileservice.data.elasticSearch.repository;

import com.filemanagement.fileservice.data.model.FileProjection;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import java.util.List;

public interface FileQueryRepository extends ElasticsearchRepository<FileProjection, String> {
    List<FileProjection> findByFileNameContaining(String fileName);
}

