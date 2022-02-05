package com.projekt.projekt.databases;

import java.util.List;

import com.projekt.projekt.tables.Building;
import com.projekt.projekt.tables.Room;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomDB extends JpaRepository<Room, Integer> {

    Building findFirstByOrderByIDDesc();

    List<Room> findAllByBudynek(Building bud);
    

}
