package com.adrian.champlonshipfootball.repository;

import com.adrian.champlonshipfootball.model.Leaderboard;
import com.adrian.champlonshipfootball.model.Season;
import com.adrian.champlonshipfootball.model.Team;
import org.hibernate.annotations.processing.SQL;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaderboardRepository extends JpaRepository<Leaderboard, Long> {
    Leaderboard findByTeamTeamIdAndSeasonSeasonId(long team, long season);
    List<Leaderboard> findLeaderboardsByCategoryCategoryIdAndSeasonSeasonIdOrderByPointsDesc(long category, long season);


}
