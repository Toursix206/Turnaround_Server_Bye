package com.toursix.turnaround.service.room;

import com.toursix.turnaround.common.exception.ValidationException;
import com.toursix.turnaround.common.type.FileType;
import com.toursix.turnaround.domain.room.data.RoomData;
import com.toursix.turnaround.domain.room.data.RoomPrivateImage;
import com.toursix.turnaround.domain.room.repository.RoomPrivateImageRepository;
import com.toursix.turnaround.service.image.provider.S3Provider;
import com.toursix.turnaround.service.image.provider.dto.request.ImageUploadFileRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

import static com.toursix.turnaround.common.exception.ErrorCode.VALIDATION_EXCEPTION;

@RequiredArgsConstructor
@Service
public class RoomImageService {

    private final RoomPrivateImageRepository roomPrivateImageRepository;
    private final S3Provider s3Provider;

    @Transactional
    public void addRoomPrivateImages(RoomData roomData, List<MultipartFile> images) {
        if (images.isEmpty()) {
            throw new ValidationException("저장할 이미지 파일이 없습니다.", VALIDATION_EXCEPTION);
        }
        if (images.size() != 4) {
            throw new ValidationException("방을 촬영한 사진 파일 4개가 필요합니다.", VALIDATION_EXCEPTION);
        }

        roomPrivateImageRepository.saveAll(
                images.stream()
                        .map(imageFile -> s3Provider.uploadFile(ImageUploadFileRequest.of(FileType.ROOM_PRIVATE_IMAGE), imageFile))
                        .map(imageUrl -> RoomPrivateImage.of(roomData, imageUrl))
                        .collect(Collectors.toList())
        );
    }
}
