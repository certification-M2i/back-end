package com.tchat.suika.dao.model;

import com.tchat.suika.dao.entities.Message;

public class MessagePostMapper {
    public static Message DtoToEntity(MessagePostDTO dto) {
        Message entity = new Message();
        entity.setContent(dto.getContent());

        return entity;
    }

}
