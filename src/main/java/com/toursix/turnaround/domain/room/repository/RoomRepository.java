package com.toursix.turnaround.domain.room.repository;

import com.toursix.turnaround.domain.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
