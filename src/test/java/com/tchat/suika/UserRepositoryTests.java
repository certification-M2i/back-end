package com.tchat.suika;

import com.tchat.suika.dao.entities.User;
import com.tchat.suika.dao.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class UserRepositoryTests {
    @Autowired
    UserRepository userRepository;

    @Test
    void testCreateUser() {
        User user = new User("toto");
        User user1 = new User("titi");
        userRepository.save(user);
        userRepository.save(user1);
    }

    @Test
    void testFindAll() {
        System.out.println(userRepository.findAll());
    }

    @Test
    void testFindById() {
        System.out.println(userRepository.findById(1L));
    }

    @Test
    void testUpdateUser() {
        Optional<User> optionalUser = userRepository.findById(2L);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUsername("test");
            userRepository.save(user);
        }
    }
    @Test
    void testDeleteUser(){
        userRepository.deleteById(2L);
    }
}
