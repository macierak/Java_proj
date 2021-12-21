package com.projekt.projekt.databases;


import com.projekt.projekt.tables.Person;


import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonDB extends JpaRepository<Person, Integer> {
}
