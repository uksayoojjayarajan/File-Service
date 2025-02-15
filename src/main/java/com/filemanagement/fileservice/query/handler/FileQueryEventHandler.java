package com.filemanagement.fileservice.query.handler;


import com.filemanagement.fileservice.command.event.FileUploadEvent;
import com.filemanagement.fileservice.command.event.FileDeleteEvent;
import com.filemanagement.fileservice.data.model.FileProjection;
import com.filemanagement.fileservice.data.repository.FileQueryRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileQueryEventHandler {
    private final FileQueryRepository fileQueryRepository;

    @EventHandler
    public void on(FileUploadEvent event) {
        fileQueryRepository.save(new FileProjection(event.getFileId(), event.getFileName(), event.getFileType(), event.getFileUrl(), event.getUserId()));
    }

    @EventHandler
    public void on(FileDeleteEvent event) {
        fileQueryRepository.deleteById(event.getFileId());
    }
}

