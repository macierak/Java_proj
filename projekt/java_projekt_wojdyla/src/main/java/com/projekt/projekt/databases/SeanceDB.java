package com.projekt.projekt.databases;

import java.util.List;

import com.projekt.projekt.tables.Seance;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SeanceDB extends JpaRepository<Seance, Integer> {
    List<Seance> findByData(String date); 


    @Query("from Seance where TO_DATE(data, 'dd/mm/yyyy') > current_date-200 and TO_DATE(data, 'dd/mm/yyyy') < current_date-190 order by TO_DATE(data, 'dd/mm/yyyy') ")
    List<Seance> findByQuery(); 
  
}
