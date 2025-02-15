package com.filemanagement.fileservice.command.service;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.filemanagement.fileservice.command.commands.UploadFileCommand;
import com.filemanagement.fileservice.command.commands.DeleteFileCommand;
import com.filemanagement.fileservice.command.commands.UpdateFileMetadataCommand;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.InputStream;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class FileCommandServiceImpl implements FileCommandService {

    private final CommandGateway commandGateway;

    @Value("${aws.s3.access.key}")
    private String awsS3AccessKey;

    @Value("${aws.s3.secret.key}")
    private String awsS3SecretKey;

    public CompletableFuture<String> uploadFile(UploadFileCommand command, MultipartFile file) {
        String saveFileUrl = saveFileTOAWSS3Bucket(file);
        command.setFileUrl(saveFileUrl);
        return commandGateway.send(command);
    }

    public CompletableFuture<String> deleteFile(DeleteFileCommand command) {
        return commandGateway.send(command);
    }

    public CompletableFuture<String> updateFileMetadata(UpdateFileMetadataCommand command) {
        return commandGateway.send(command);
    }


    private String saveFileTOAWSS3Bucket(MultipartFile file){
        try{
            String S3FileName = file.getOriginalFilename();
            BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsS3AccessKey, awsS3SecretKey);
            AmazonS3 amazonS3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                                       .withRegion(Regions.US_EAST_1).build();

            InputStream  inputStream = file.getInputStream();
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            String bucketName ="file-upload-file-management";
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, S3FileName, inputStream, objectMetadata);
            amazonS3Client.putObject(putObjectRequest);
            return  "https://" + bucketName + ".s3.amazonaws.com/" + S3FileName;
        } catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
