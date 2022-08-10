package com.toursix.turnaround.service.room.dto.request;

import com.toursix.turnaround.domain.room.data.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateRoomDataRequestDto {

    @ApiModelProperty(value = "방 구조 - 정사각형", example = "TRUE")
    private RoomDataStatus square;

    @ApiModelProperty(value = "방 구조 - 직사각형", example = "FALSE")
    private RoomDataStatus rectangle;

    @ApiModelProperty(value = "방 구조 - 복층형", example = "FALSE")
    private RoomDataStatus duplex;

    @ApiModelProperty(value = "취침공간 - 침대", example = "TRUE")
    private RoomDataStatus bed;

    @ApiModelProperty(value = "취침공간 - 침구류", example = "TRUE")
    private RoomDataStatus bedding;

    @ApiModelProperty(value = "세탁공간 - 세탁기", example = "TRUE")
    private RoomDataStatus washer;

    @ApiModelProperty(value = "세탁공간 - 빨래걸이", example = "TRUE")
    private RoomDataStatus washHanger;

    @ApiModelProperty(value = "사무공간 - 책상", example = "TRUE")
    private RoomDataStatus desk;

    @ApiModelProperty(value = "사무공간 - 의자", example = "FALSE")
    private RoomDataStatus chair;

    @ApiModelProperty(value = "사무공간 - 컴퓨터", example = "FALSE")
    private RoomDataStatus computer;

    @ApiModelProperty(value = "기타공간 - 주방", example = "FALSE")
    private RoomDataStatus kitchen;

    @ApiModelProperty(value = "기타공간 - 화장실", example = "TRUE")
    private RoomDataStatus restroom;

    @ApiModelProperty(value = "기타공간 - 창문", example = "FALSE")
    private RoomDataStatus window;

    public RoomData toEntity() {
        return RoomData.of(
                RoomStructure.of(square, rectangle, duplex),
                RoomSpaceSeperation.of(
                        RoomSleep.of(bed, bedding),
                        RoomLaundry.of(washer, washHanger),
                        RoomOffice.of(desk, chair, computer),
                        RoomElseSpace.of(kitchen, restroom, window)
                )
        );
    }
}
