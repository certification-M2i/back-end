package com.tchat.suika.model.mapper;

import com.tchat.suika.dao.entities.Message;
import com.tchat.suika.model.dtos.MessagePostDTO;

public class MessagePostMapper {
    public static Message DtoToEntity(MessagePostDTO dto) {
        Message entity = new Message();
        entity.setContent(dto.getContent());

        return entity;
    }

}
