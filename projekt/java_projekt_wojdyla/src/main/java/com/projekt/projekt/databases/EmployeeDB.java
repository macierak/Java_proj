package com.projekt.projekt.databases;




import java.util.List;

import com.projekt.projekt.tables.Employee;
import com.projekt.projekt.tables.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeDB extends JpaRepository<Employee, Integer> {


    Employee getByKonto(User user);

    @Query(value = "select nazwisko, imie, 2200 + count(*)*200 as wypłata from pracownik left join seans_pracownik on pracownik._id = seans_pracownik.pracownik__id left join seans on seans.id = seans_pracownik.seance_id where TO_DATE(seans.data, 'dd/mm/yyyy') > current_date-193 and TO_DATE(seans.data, 'dd/mm/yyyy') < current_date-163 group by nazwisko, imie order by count(*) desc", nativeQuery = true)
    List<List<String>> raportData();
    @Query(value = "select nazwisko, imie,  count(*) as wypłata from pracownik left join seans_pracownik on pracownik._id = seans_pracownik.pracownik__id left join seans on seans.id = seans_pracownik.seance_id  group by nazwisko, imie order by count(*) desc", nativeQuery = true)
    List<List<String>> raportData2();
}
