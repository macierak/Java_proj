package com.projekt.projekt.databases;

import java.util.List;

import com.projekt.projekt.tables.Seance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SeanceDB extends JpaRepository<Seance, Integer> {
    List<Seance> findByData(String date); 


    @Query("from Seance where TO_DATE(data, 'dd/mm/yyyy') > current_date-200 and TO_DATE(data, 'dd/mm/yyyy') < current_date-193 order by TO_DATE(data, 'dd/mm/yyyy') ")
    List<Seance> findByQuery(); 
    @Query("from Seance where TO_DATE(data, 'dd/mm/yyyy') > current_date-192 and TO_DATE(data, 'dd/mm/yyyy') < current_date-185 order by TO_DATE(data, 'dd/mm/yyyy') ")
    List<Seance> findByQuery1(); 
    
    @Query(value = "select s.id,s.data, s.sala_id, s.nazwa_filmu, count(*) from seans s left join rezerwacja r on s.id = r.seans_id where r.paid = true  group by s.id, s.nazwa_filmu order by s.id desc", nativeQuery = true)
    List<List<String>> findByQuery2();
    @Query(value = "select data, nazwa_filmu, count(*) from seans left join seans_pracownik on seans.id = seans_pracownik.seance_id where  TO_DATE(seans.data, 'dd/mm/yyyy') > current_date-193 and TO_DATE(seans.data, 'dd/mm/yyyy') < current_date-163 group by data, nazwa_filmu order by TO_DATE(seans.data, 'dd/mm/yyyy')", nativeQuery = true)
    List<List<String>> findByQuery3();
}
