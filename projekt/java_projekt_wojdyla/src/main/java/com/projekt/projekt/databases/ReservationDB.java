package com.projekt.projekt.databases;

import java.util.List;

import com.projekt.projekt.tables.Reservation;
import com.projekt.projekt.tables.Seance;
import com.projekt.projekt.tables.User;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ReservationDB extends JpaRepository<Reservation, Integer> {
    List<Reservation> findByKonto(User user);
    List<Reservation> findAllBySeans(Seance seance);
    List<Reservation> findAllByKonto(User user);
    List<Reservation> findAllByPaid(boolean b);
    List<Reservation> findAllByPaidOrderBySeans(boolean b);

}
