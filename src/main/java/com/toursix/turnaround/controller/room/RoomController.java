package com.toursix.turnaround.controller.room;

import com.toursix.turnaround.common.dto.ErrorResponse;
import com.toursix.turnaround.common.dto.SuccessResponse;
import com.toursix.turnaround.common.success.SuccessCode;
import com.toursix.turnaround.config.interceptor.Auth;
import com.toursix.turnaround.service.room.RoomService;
import com.toursix.turnaround.service.room.dto.request.CreateRoomDataRequestDto;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @ApiOperation(
            value = "[인증] 방 세팅 페이지 - 방 사진과 라벨링 정보를 저장합니다.",
            notes = "방을 촬영한 이미지 파일 4장과 라벨링 정보를 multipart/form-data 형식으로 받습니다.\n" +
                    "라벨링 정보 선택 유무는 TRUE, FALSE 로 입력받습니다.\n" +
                    "이미지 파일이 존재하지 않거나 4장이 아닌 경우 400 에러가 납니다."
    )
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "방 라벨링 데이터 생성 성공입니다."),
            @ApiResponse(code = 400, message = "잘못된 요청입니다.", response = ErrorResponse.class),
            @ApiResponse(code = 401, message = "토큰이 만료되었습니다. 다시 로그인 해주세요.", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "예상치 못한 서버 에러가 발생하였습니다.", response = ErrorResponse.class)
    })
    @Auth
    @PostMapping("/v1/room/data")
    public ResponseEntity<String> createRoomData(@Valid CreateRoomDataRequestDto request,
                                                 @ApiParam(name = "images", value = "방 촬영 이미지 파일 4개", required = true)
                                                 @RequestPart List<MultipartFile> images) {
        roomService.createRoomData(request, images);
        return SuccessResponse.success(SuccessCode.CREATE_ROOM_DATA_SUCCESS, null);
    }
}
