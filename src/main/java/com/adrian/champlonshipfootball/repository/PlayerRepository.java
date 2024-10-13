package com.adrian.champlonshipfootball.repository;

import com.adrian.champlonshipfootball.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
