package com.tchat.suika.service;

import com.tchat.suika.dao.entities.Channel;
import com.tchat.suika.dao.entities.User;
import com.tchat.suika.dao.repositories.ChannelRepository;
import com.tchat.suika.dao.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ChannelRepository channelRepository;

    @Autowired
    ChannelService channelService;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void createUser(User user) {
        User saveUser = userRepository.save(user);
        Channel channel = channelRepository.findByName("Général")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ce channel n'existe pas"));
        channelService.assignUserToChannel(channel.getId(), saveUser.getId());
    }

    public User updateUser(Long id, String username) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur inexistant pour cet id"));
        user.setUsername(username);
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
