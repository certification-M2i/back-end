package com.tchat.suika.restController;

import com.tchat.suika.dao.entities.User;
import com.tchat.suika.model.dtos.UserDTO;
import com.tchat.suika.model.mapper.UserMapper;
import com.tchat.suika.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("users")
    public List<UserDTO> getUsers() {
        List<User> users = userService.getUsers();
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user : users) {
            userDTOS.add(UserMapper.convertToDTO(user));
        }
        return userDTOS;
    }

    @GetMapping("users/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") Long id) {
        Optional<User> optionalUser = userService.getUserById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            UserDTO userDTO = UserMapper.convertToDTO(user);
            return ResponseEntity.ok(userDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("users")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity addUser(@RequestBody UserDTO userDTO) {
        User user = UserMapper.convertToEntity(userDTO);
        userService.createUser(user);
        return ResponseEntity.ok(user);
    }

    @PutMapping("users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestParam String username) {
        User updatedUser = userService.updateUser(id, username);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Long id) {
        User user = userService.getUserById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur inexistant pour cet id"));
        if (user.getId() == null){
            return ResponseEntity.notFound().build();
        }
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

}
