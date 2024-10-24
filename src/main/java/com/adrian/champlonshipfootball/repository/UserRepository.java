package com.adrian.champlonshipfootball.repository;

import org.springframework.stereotype.Repository;
import com.adrian.champlonshipfootball.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);

}
