package com.example.gymtracker.Service.Impl;


import com.example.gymtracker.Domain.entities.Users;
import com.example.gymtracker.Repository.UsersRepository;
import com.example.gymtracker.Service.UserService;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    UsersRepository usersRepository;

    public UserServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<Users> getUsers(){
        List<Users> usersList = new ArrayList<>();
        usersRepository.findAll().forEach(usersList::add);
        return usersList;
    }

    @Override
    public Users getUserById(Long id) {

        Optional<Users> usersOptional = usersRepository.findById(id);
        return usersOptional.orElse(null);
    }

    @Override
    public Users save(Users users) {
        return usersRepository.save(users);
    }

    public boolean isExists(Long id) {
        Optional<Users> optionalUsers = usersRepository.findById(id);
        return optionalUsers.isPresent();
    }

    @Override
    public void deleteUser(Long id) {
        usersRepository.deleteById(id);
    }

    @Override
    public List<Users> getUsersByEndDate(LocalDate endDate) {
        Optional<List<Users>> optionalUsers = usersRepository.findByEndDate(endDate);
        return optionalUsers.<List<Users>>map(ArrayList::new).orElse(null);

    }

}
