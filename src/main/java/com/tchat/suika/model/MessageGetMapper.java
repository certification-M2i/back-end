package com.tchat.suika.model;

import com.tchat.suika.dao.entities.Message;

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
