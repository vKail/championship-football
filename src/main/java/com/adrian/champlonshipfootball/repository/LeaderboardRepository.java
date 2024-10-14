package com.adrian.champlonshipfootball.repository;

import com.adrian.champlonshipfootball.model.Leaderboard;
import com.adrian.champlonshipfootball.model.Season;
import com.adrian.champlonshipfootball.model.Team;
import org.hibernate.annotations.processing.SQL;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaderboardRepository extends JpaRepository<Leaderboard, Long> {
    Leaderboard findByTeamAndSeason(Team team, Season season);

}
