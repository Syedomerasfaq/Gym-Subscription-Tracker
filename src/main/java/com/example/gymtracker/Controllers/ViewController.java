package com.example.gymtracker.Controllers;

import com.example.gymtracker.Domain.Dto.UsersDto;
import com.example.gymtracker.Domain.entities.Users;
import com.example.gymtracker.Mappers.Impl.UsersMapperImpl;
import com.example.gymtracker.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {


    private final UserService userService;
    private final UsersMapperImpl usersMapper;

    public ViewController(UserService userService, UsersMapperImpl usersMapper) {
        this.userService = userService;
        this.usersMapper = usersMapper;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/userslist")
    public String getSubs() {
        return "users";
    }

    @GetMapping("/addList")
    public String addSubs() {
        return "add";
    }

    @GetMapping("/updateList")
    public String updateSubs(@RequestParam(name = "id",required = false) Long userId, Model model) {
        if (userService.getUserById(userId)!=null){
            UsersDto users = usersMapper.mapTo(userService.getUserById(userId));
            model.addAttribute("userId", userId);
            model.addAttribute("userName", users.getName());
            model.addAttribute("startDate", users.getStartDate());
            model.addAttribute("endDate", users.getEndDate());
            model.addAttribute("frequency", users.getFrequency());
            model.addAttribute("type", users.getType());
        }
        return "update";
    }

    @GetMapping("/remainder")
    public String getRemainder() {
        return "remainder";
    }
}
