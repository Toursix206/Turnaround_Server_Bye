package com.toursix.turnaround.service.room;

import com.toursix.turnaround.domain.room.data.RoomData;
import com.toursix.turnaround.domain.room.repository.RoomDataRepository;
import com.toursix.turnaround.service.room.dto.request.CreateRoomDataRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class RoomService {

    private final RoomImageService roomImageService;
    private final RoomDataRepository roomDataRepository;

    @Transactional
    public void createRoomData(CreateRoomDataRequestDto request, List<MultipartFile> images) {
        RoomData roomData = roomDataRepository.save(request.toEntity());
        roomImageService.addRoomPrivateImages(roomData, images);
    }
}
