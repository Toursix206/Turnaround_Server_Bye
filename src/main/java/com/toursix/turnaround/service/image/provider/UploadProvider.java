package com.toursix.turnaround.service.image.provider;

import com.toursix.turnaround.service.image.client.S3FileStorageClient;
import com.toursix.turnaround.service.image.provider.dto.request.UploadFileRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Component
public class UploadProvider {

    private final S3FileStorageClient fileStorageClient;

    public String uploadFile(UploadFileRequest request, MultipartFile file) {
        request.validateAvailableContentType(file.getContentType());
        String fileName = request.getFileNameWithBucketDirectory(file.getOriginalFilename());
        fileStorageClient.uploadFile(file, fileName);
        return fileStorageClient.getFileUrl(fileName);
    }
}
