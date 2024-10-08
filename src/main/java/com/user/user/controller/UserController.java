package com.user.user.controller;

import com.user.user.dto.UserDTO;
import com.user.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/")
@SpringBootApplication

public class UserController {
    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getuser")
    public List<UserDTO> getUser() {
        return userService.getAllUsers();
    }

    @PostMapping("/adduser")
    public String addUser(@RequestBody UserDTO userDTO) {
        return userService.addUser(userDTO);
    }

    @PutMapping("/updateuser")
    public String updateUser(@RequestBody UserDTO userDTO) {
        return userService.updateUser(userDTO);
    }

    @DeleteMapping("/deleteuser")
    public String deleteUser(@RequestBody UserDTO userDTO) {
        return userService.deleteUser(userDTO);
    }

    @DeleteMapping("/deleteuser/{userId}")
    public String deleteUserById(@PathVariable Integer userId) {
        return userService.deleteUserById(userId);
    }

    @GetMapping("/getuser/{userId}")
    public UserDTO getUserById(@PathVariable Integer userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/updateuser/{userId}")
    public String updateUserById(@PathVariable Integer userId, @RequestBody UserDTO userDTO) {
        return userService.updateUserById(userId,userDTO);
    }


}
