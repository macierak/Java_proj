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
    @Query(value = "select p._id, p.nazwisko, u.login, count(case when TO_DATE(s.data, 'dd/mm/yyyy') < current_date-193 then 1 end ) as przeszłe, count(case when TO_DATE(s.data, 'dd/mm/yyyy') > current_date-193 then 1 end ) as przyszłe from pracownik p left join użytkownik u on u.id = p.konto_id  left join seans_pracownik sp on sp.pracownik__id = p._id left join seans s on s.id = sp.seance_id  group by p.nazwisko, u.login, p._id order by p._id", nativeQuery = true)
    List<List<String>> raportData2();
}
