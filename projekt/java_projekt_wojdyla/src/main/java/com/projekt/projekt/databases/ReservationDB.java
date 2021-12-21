package com.projekt.projekt.databases;

import java.util.List;

import com.projekt.projekt.tables.Reservation;
import com.projekt.projekt.tables.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationDB extends JpaRepository<Reservation, Integer> {
    List<Reservation> findByKonto(User user);
}
