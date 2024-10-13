package com.adrian.champlonshipfootball.repository;

import com.adrian.champlonshipfootball.model.Leaderboard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaderboardRepository extends JpaRepository<Leaderboard, Long> {
}
