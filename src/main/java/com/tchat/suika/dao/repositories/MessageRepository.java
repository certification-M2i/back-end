package com.tchat.suika.dao.repositories;

import com.tchat.suika.dao.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
