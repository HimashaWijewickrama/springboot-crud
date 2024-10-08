package com.user.user.service;

import com.user.user.dto.UserDTO;
import com.user.user.model.User;
import com.user.user.repo.UserRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<UserDTO> getAllUsers() {
        List<User> userList = userRepo.findAll();
        return modelMapper.map(userList, new TypeToken<List<UserDTO>>(){}.getType());
    }

    public String addUser(UserDTO userDTO) {
        userRepo.save(modelMapper.map(userDTO, User.class));
        return "User has been added";
    }

    public String updateUser(UserDTO userDTO) {
        userRepo.save(modelMapper.map(userDTO, User.class));
        return "User has been updated";
    }

    public String deleteUser(UserDTO userDTO) {
        userRepo.delete(modelMapper.map(userDTO,User.class));
        return "User has been deleted";
    }

    public String deleteUserById(Integer userId){
        userRepo.deleteById(userId);
        return "User has been deleted";
    }

    public UserDTO getUserById(Integer userId){
        User user = userRepo.findById(userId).get();
        return modelMapper.map(user, UserDTO.class);
    }

    public String updateUserById(Integer userId, UserDTO userDTO) {
        Optional<User> existingUserOpt = userRepo.findById(userId);
        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();
            // Update user details
            existingUser.setFirstName(userDTO.getFirstName());
            existingUser.setLastName(userDTO.getLastName());
            existingUser.setEmail(userDTO.getEmail());
            existingUser.setPhoneNumber(userDTO.getPhoneNumber());
            existingUser.setUserName(userDTO.getUserName());
            existingUser.setPassword(userDTO.getPassword()); // Be cautious with passwords!

            userRepo.save(existingUser);
            return "User has been updated";
        } else {
            return "User not found";
        }
    }
}
