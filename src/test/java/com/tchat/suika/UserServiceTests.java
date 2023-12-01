package com.tchat.suika;

import com.tchat.suika.dao.entities.User;
import com.tchat.suika.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class UserServiceTests {
    @Autowired
    UserService userService;

    @Test
    void testCreateUser() {
        User user = new User("Mickaël");
        User user1 = new User("Bob");
        userService.createUser(user);
        userService.createUser(user1);
    }

    @Test
    public void testFindAll() {
        System.out.println(userService.getUsers());
    }

    @Test
    void testFindById() {
        System.out.println(userService.getUserById(1L));
    }

    @Test
    void testUpdateUser() {
        Optional<User> optionalUser = userService.getUserById(3L);
        if (optionalUser.isPresent()) {
            Long userId = optionalUser.get().getId();
            userService.updateUser(userId,"test");
        }
    }

    @Test
    void testDeleteUser() {
        userService.deleteUser(4L);
    }
}
