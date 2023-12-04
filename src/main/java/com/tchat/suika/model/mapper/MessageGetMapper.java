package com.tchat.suika.model.mapper;

import com.tchat.suika.dao.entities.Message;
import com.tchat.suika.model.dtos.MessageGetDTO;

public class MessageGetMapper {
    public static MessageGetDTO entityToDto(Message entity) {
        MessageGetDTO messageDTO = new MessageGetDTO();

        messageDTO.setContent(entity.getContent());
        messageDTO.setSendingDate(entity.getSendingDate());
        messageDTO.setUserName(entity.getUser().getUsername());
        messageDTO.setIdChannel(entity.getChannel().getId());

        return messageDTO;
    }
}
