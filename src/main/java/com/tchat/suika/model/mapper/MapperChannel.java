package com.tchat.suika.model.mapper;

import com.tchat.suika.dao.entities.Channel;
import com.tchat.suika.dao.entities.User;
import com.tchat.suika.model.dtos.ChannelCreationDTO;
import com.tchat.suika.model.dtos.GetChannelDTO;

public class MapperChannel {

    public static Channel channelCreationDTOToChannel(ChannelCreationDTO dto, User creator) {
        if (dto == null) {
            return null;
        }

        Channel channel = new Channel();
        channel.setName(dto.getName());

        if (creator != null) {
            channel.getUsers().add(creator);
        }

        return channel;
    }
    public static ChannelCreationDTO channelToChannelCreationDTO(Channel channel) {
        if (channel == null) {
            return null;
        }

        ChannelCreationDTO dto = new ChannelCreationDTO();
        dto.setName(channel.getName());
        if (!channel.getUsers().isEmpty()) {
            dto.setCreatorUsername(channel.getUsers().get(0).getUsername());
        }

        return dto;
    }

    public GetChannelDTO ChanneltoGetChannelDTO(Channel channel) {
        return new GetChannelDTO(channel.getId(), channel.getName(), channel.getDateCreation());
    }
}
