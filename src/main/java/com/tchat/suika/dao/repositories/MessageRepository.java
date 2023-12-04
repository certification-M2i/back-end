package com.tchat.suika.dao.repositories;

import com.tchat.suika.dao.entities.Message;
import com.tchat.suika.model.dtos.GetMessagesInChannelDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("SELECT new com.tchat.suika.model.dtos.GetMessagesInChannelDTO(m.id, m.content, m.sendingDate, m.user.username) " +
            "FROM Message m WHERE m.channel.id = :channelId")
    List<GetMessagesInChannelDTO> findMessagesByChannelId(Long channelId);
}
