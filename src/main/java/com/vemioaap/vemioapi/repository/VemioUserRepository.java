package com.vemioaap.vemioapi.repository;

import com.vemioaap.vemioapi.model.VemioUsers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VemioUserRepository extends JpaRepository<VemioUsers, Integer> {
}
