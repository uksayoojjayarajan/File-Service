package com.filemanagement.fileservice.data.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "file_index")
public class FileProjection {
    @Id
    private String fileId;
    private String fileName;
    private String fileType;
    private String fileUrl;
    private String userId;
}

