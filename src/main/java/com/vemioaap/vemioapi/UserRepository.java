package com.vemioaap.vemioapi;

import com.vemioaap.vemioapi.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
