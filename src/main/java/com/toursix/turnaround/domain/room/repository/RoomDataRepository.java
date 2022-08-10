package com.toursix.turnaround.domain.room.repository;

import com.toursix.turnaround.domain.room.data.RoomData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomDataRepository extends JpaRepository<RoomData, Long> {
}
