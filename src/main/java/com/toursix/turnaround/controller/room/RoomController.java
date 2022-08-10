package com.toursix.turnaround.controller.room;

import com.toursix.turnaround.common.dto.ApiResponse;
import com.toursix.turnaround.config.interceptor.Auth;
import com.toursix.turnaround.service.room.RoomService;
import com.toursix.turnaround.service.room.dto.request.CreateRoomDataRequestDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Room")
@RequiredArgsConstructor
@RestController
public class RoomController {

    private final RoomService roomService;

    @ApiOperation("[인증] 방 세팅 페이지 - 방 사진과 라벨링 정보를 저장합니다.")
    @Auth
    @PostMapping("/v1/room/data")
    public ApiResponse<String> createRoomData(@Valid CreateRoomDataRequestDto request,
                                              @ApiParam(name = "images", value = "방 촬영 이미지 파일 4개", required = true)
                                              @RequestPart List<MultipartFile> images) {
        roomService.createRoomData(request, images);
        return ApiResponse.SUCCESS;
    }
}
