package com.projekt.projekt.databases;

import com.projekt.projekt.tables.Room;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomDB extends JpaRepository<Room, Integer> {

}
