package com.tchat.suika.dao.repositories;

import com.tchat.suika.dao.entities.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelRepository extends JpaRepository <Channel, Long> {
}
