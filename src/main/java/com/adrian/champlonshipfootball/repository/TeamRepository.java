package com.adrian.champlonshipfootball.repository;

import com.adrian.champlonshipfootball.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
