package com.tchat.suika.dao.repositories;

import com.tchat.suika.dao.entities.Channel;
import com.tchat.suika.model.dtos.GetChannelDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ChannelRepository extends JpaRepository <Channel, Long> {

    @Query("SELECT new com.tchat.suika.model.dtos.GetChannelDTO(c.id, c.name, c.dateCreation) " +
            "FROM Channel c LEFT JOIN c.users u " +
            "WHERE c.isDefault = true OR u.username = :username")
    List<GetChannelDTO> findChannelsByUserUsername(String username);

    Optional<Channel> findByName(String name);
}
