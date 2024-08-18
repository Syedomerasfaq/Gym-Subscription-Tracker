package com.example.gymtracker.Controllers;

import com.example.gymtracker.Domain.Dto.UsersDto;
import com.example.gymtracker.Domain.entities.Users;
import com.example.gymtracker.Mappers.Impl.UsersMapperImpl;
import com.example.gymtracker.Service.UserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UsersController {

    private final UserService userService;
    private final UsersMapperImpl usersMapper;

    public UsersController(UserService userService, UsersMapperImpl usersMapper) {
        this.userService = userService;
        this.usersMapper = usersMapper;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UsersDto>> getUsers(@RequestParam(name = "sortColumn", required = false) String sortColumn , @RequestParam(name = "sortOrder", required = false) String sortOrder){
        List<Users> usersList = userService.getUsers(sortColumn,sortOrder);
        List<UsersDto> usersDtoList = new ArrayList<>();
        for (Users users : usersList){
            usersDtoList.add(usersMapper.mapTo(users));
        }
        return new ResponseEntity<>(usersDtoList,HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    private ResponseEntity<UsersDto> getUserById(@PathVariable Long id){
        if (userService.getUserById(id)!=null){
            return new ResponseEntity<>(usersMapper.mapTo(userService.getUserById(id)),HttpStatus.FOUND);
        }
        else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/users")
    public ResponseEntity<UsersDto> getUsers(@RequestBody UsersDto usersDto){
        Users user = usersMapper.mapFrom(usersDto);
        Users savedUser = userService.save(user);
        return new ResponseEntity<>(usersMapper.mapTo(savedUser),HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UsersDto> UpdateUsers(@PathVariable Long id, @RequestBody UsersDto usersDto){
        Users user = usersMapper.mapFrom(usersDto);
        if (!userService.isExists(id)){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        user.setId(id);
        Users savedUser = userService.save(user);
        return new ResponseEntity<>(usersMapper.mapTo(savedUser),HttpStatus.CREATED);
    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Users> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
    }

    @GetMapping("/users/findByEndDate")
    public ResponseEntity<List<UsersDto>> findUsersByEndDate(@RequestParam(name = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate currentDate) {
        if (userService.getUsersByEndDate(currentDate)!=null){
           List<Users> usersList = userService.getUsersByEndDate(currentDate);
           List<UsersDto> usersDtoList = new ArrayList<>();
           for (Users users : usersList){
               usersDtoList.add(usersMapper.mapTo(users));
           }
           return new ResponseEntity<>(usersDtoList,HttpStatus.OK);
       }
       else
           return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @GetMapping("/users/findByName")
    public ResponseEntity<List<UsersDto>> findUsersByName(@RequestParam(name = "name", required = false) String name,@RequestParam(name = "sortColumn", required = false) String sortColumn , @RequestParam(name = "sortOrder", required = false) String sortOrder) {
        if (userService.getUsersByName(name,sortColumn,sortOrder)!=null){
            List<Users> usersList = userService.getUsersByName(name,sortColumn,sortOrder);
            List<UsersDto> usersDtoList = new ArrayList<>();
            for (Users users : usersList){
                usersDtoList.add(usersMapper.mapTo(users));
            }
            return new ResponseEntity<>(usersDtoList,HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }
}
