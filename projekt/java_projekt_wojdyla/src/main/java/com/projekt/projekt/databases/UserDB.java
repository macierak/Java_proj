package com.projekt.projekt.databases;


import com.projekt.projekt.tables.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDB extends JpaRepository<User, Integer> {
    User findFirstByLoginAndHasło(String im, String naz);
    User findFirstByID(int id);
    User findFirstByLogin(String login);
}
