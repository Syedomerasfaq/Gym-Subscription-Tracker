package com.example.gymtracker.Service.Impl;


import com.example.gymtracker.Domain.entities.Users;
import com.example.gymtracker.Repository.UsersRepository;
import com.example.gymtracker.Service.UserService;
import org.apache.catalina.User;
import org.springframework.data.domain.Sort;
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
    public List<Users> getUsers(String sortColumn, String sortOrder){
        List<Users> usersList = new ArrayList<>();
        if (sortColumn!=null && sortOrder!=null && sortOrder.equals("asc")){
            usersList.addAll(usersRepository.findAll(Sort.by(Sort.Direction.ASC, sortColumn)));
        }
        else if (sortColumn!=null && sortOrder!=null && sortOrder.equals("desc")){
            usersList.addAll(usersRepository.findAll(Sort.by(Sort.Direction.DESC, sortColumn)));
        }
        else {
            usersList.addAll(usersRepository.findAll());
        }
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

    @Override
    public List<Users> getUsersByName(String name,String sortColumn, String sortOrder) {
        Optional<List<Users>> optionalUsers;
        if (!sortColumn.isEmpty() && !sortOrder.isEmpty()) {
            Sort sort = sortOrder.equalsIgnoreCase("asc") ?
                    Sort.by(Sort.Direction.ASC, sortColumn) :
                    Sort.by(Sort.Direction.DESC, sortColumn);

            optionalUsers = usersRepository.findByNameContaining(name, sort);
        } else {
            optionalUsers = usersRepository.findByNameContaining(name);
        }
        return optionalUsers.<List<Users>>map(ArrayList::new).orElse(null);
    }

}
