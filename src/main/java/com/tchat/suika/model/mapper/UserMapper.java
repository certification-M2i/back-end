package com.tchat.suika.model.mapper;

import com.tchat.suika.dao.entities.User;
import com.tchat.suika.model.dtos.UserDTO;

public class UserMapper {

    public static UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        return userDTO;
    }

    public static User convertToEntity(UserDTO userDTO){
        User user = new User();
        user.setUsername(userDTO.getUsername());
        return user;
    }

}
