package com.tchat.suika.dao.repositories;

import com.tchat.suika.dao.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
