package com.filemanagement.fileservice.query.controller;

import com.filemanagement.fileservice.data.repository.FileQueryRepository;
import com.filemanagement.fileservice.data.model.FileProjection;
import com.filemanagement.fileservice.query.service.FileQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileQueryController {
    private final FileQueryService fileQueryService;
    @GetMapping("/{fileId}")
    public FileProjection getFile(@PathVariable String fileId) {
        return fileQueryService.getFile(fileId);
    }

    @GetMapping("/search")
    public ResponseEntity<List<FileProjection>> GetAllFiles() {
      return ResponseEntity.ok(fileQueryService.getAllFiles());
    }


    @GetMapping("/search")
    public List<FileProjection> searchFiles(@RequestParam String query) {
        return fileQueryService.searchFiles(query);
    }
}

