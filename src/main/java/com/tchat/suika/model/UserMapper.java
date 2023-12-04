package com.tchat.suika.model;

import com.tchat.suika.dao.entities.User;

public class UserMapper {

    public static UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        return userDTO;
    }

    public static User convertToEntity(UserDTO userDTO){
        User user = new User();
        user.setUsername(userDTO.getUsername());
        return user;
    }

}
