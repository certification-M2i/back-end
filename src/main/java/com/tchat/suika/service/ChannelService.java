package com.tchat.suika.service;

import com.tchat.suika.dao.entities.Channel;
import com.tchat.suika.dao.entities.User;
import com.tchat.suika.dao.repositories.ChannelRepository;
import com.tchat.suika.dao.repositories.MessageRepository;
import com.tchat.suika.dao.repositories.UserRepository;
import com.tchat.suika.model.ChannelCreationDTO;
import com.tchat.suika.model.GetChannelDTO;
import com.tchat.suika.model.GetMessagesInChannelDTO;
import com.tchat.suika.model.MapperChannel;
import jakarta.transaction.Transactional;
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

        channel.setName(newName);
        return channelRepository.save(channel);
    }

    public void deleteChannel(Long id) {
        Channel channel = channelRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ce channel n'existe pas"));
        if(channel.isDefault()){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Ce channel ne peut être supprimé");
        }

        channelRepository.deleteById(id);
    }

}
