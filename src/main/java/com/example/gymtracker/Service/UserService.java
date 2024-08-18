package com.example.gymtracker.Service;

import com.example.gymtracker.Domain.entities.Users;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface UserService {

    List<Users> getUsers(String sortColumn, String sortOrder);
    Users getUserById(Long id);

    Users save(Users users);

    boolean isExists(Long id);

    void deleteUser(Long id);
    List<Users> getUsersByEndDate(LocalDate endDate);
    List<Users> getUsersByName(String name,String sortColumn, String sortOrder);

}
