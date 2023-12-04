package com.tchat.suika.service;

import com.tchat.suika.dao.entities.Channel;
import com.tchat.suika.dao.entities.User;
import com.tchat.suika.dao.repositories.ChannelRepository;
import com.tchat.suika.dao.repositories.MessageRepository;
import com.tchat.suika.dao.repositories.UserRepository;
import com.tchat.suika.model.dtos.ChannelCreationDTO;
import com.tchat.suika.model.dtos.GetChannelDTO;
import com.tchat.suika.model.dtos.GetMessagesInChannelDTO;
import com.tchat.suika.model.mapper.MapperChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ChannelService {
    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    public ChannelCreationDTO saveChannel(ChannelCreationDTO channelCreationDto) {
        User creator = userRepository.findByUsername(channelCreationDto.getCreatorUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cet utilisateur n'existe pas"));

        Channel channel = MapperChannel.channelCreationDTOToChannel(channelCreationDto, creator);

        channelRepository.save(channel);
        return channelCreationDto;
    }

    public List<GetMessagesInChannelDTO> getMessagesByChannelId(Long channelId) {
        if(!channelRepository.existsById(channelId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ce channel n'existe pas");
        }
        return messageRepository.findMessagesByChannelId(channelId);
    }

    public Optional<Channel> getChannelById(Long id) {
        return channelRepository.findById(id);
    }

    public List<GetChannelDTO> getAllChannels(String username) {
        return channelRepository.findChannelsByUserUsername(username);
    }

    public Channel renameChannel(Long channelId, String newName) {
        Channel channel = channelRepository.findById(channelId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ce channel n'existe pas"));
        if(channel.isDefault()){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Ce channel ne peut être modifié");
        }

        channel.setName(newName);
        return channelRepository.save(channel);
    }

    public Long deleteChannel(Long id) {
        Channel channel = channelRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ce channel n'existe pas"));
        if(channel.isDefault()){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Ce channel ne peut être supprimé");
        }

        channelRepository.deleteById(id);
        return id;
    }

    public String assignUserToChannel(Long channelId, Long userId) {
        Channel channel = channelRepository.findById(channelId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ce channel n'existe pas"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cet utilisateur n'existe pas"));

        if(channel.getUsers().contains(user)){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "L'utilisateur est déjà dans ce channel");
        }

        channel.getUsers().add(user);
        channelRepository.save(channel);
        return "Utilisateur affecté";
    }

    public String removeUserFromChannel(Long channelId, Long userId) {
        Channel channel = channelRepository.findById(channelId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ce channel n'existe pas"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cet utilisateur n'existe pas"));

        if (channel.getUsers().size() <= 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le channel doit contenir au moins un utilisateur");
        }

        if (!channel.getUsers().contains(user)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "L'utilisateur n'est pas dans ce channel");
        }
        if(channel.isDefault()){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "l'utilisateur ne peut être supprimé du channel par défaut");
        }

        channel.getUsers().remove(user);
        channelRepository.save(channel);
        return "Utilisateur supprimé du channel";
    }
}
