package com.tchat.suika.service;

import com.tchat.suika.dao.entities.Channel;
import com.tchat.suika.dao.entities.Message;
import com.tchat.suika.dao.entities.User;
import com.tchat.suika.model.MessagePostDTO;
import com.tchat.suika.model.MessagePostMapper;
import com.tchat.suika.dao.repositories.ChannelRepository;
import com.tchat.suika.dao.repositories.MessageRepository;
import com.tchat.suika.dao.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ChannelRepository channelRepository;


    public List<Message> getMessages(){
        return messageRepository.findAll();
    }

    public Optional<Message> getMessage(Long id) {
        return messageRepository.findById(id);
    }

    public MessagePostDTO createMessage(MessagePostDTO messagePostDTO) {
        Message message = MessagePostMapper.DtoToEntity(messagePostDTO);
        User user = userRepository.findById(messagePostDTO.getIdUser())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "L'utilisateur n'existe pas"));
        message.setUser(user);

        Channel channel = channelRepository.findById(messagePostDTO.getIdChannel())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Le canal n'existe pas"));
        message.setChannel(channel);

        messageRepository.save(message);
        return messagePostDTO;
    }

    public boolean emptyField(MessagePostDTO messagePostDTO) {
        if (messagePostDTO.getContent() == null || messagePostDTO.getIdUser() == null || messagePostDTO.getIdChannel() == null) {
            return true;
        }
        return false;
    }
}
