package com.adrian.champlonshipfootball.repository;

import com.adrian.champlonshipfootball.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MatchRepository extends JpaRepository<Match, Long> {
    @Query("UPDATE Match m SET m.status = ?2 WHERE m.matchId = ?1")
    Match updateMatchByStatus(long id, Match match);
    @Query("UPDATE Match m SET m.result = ?2 WHERE m.matchId = ?1")
    String updateMatchByResult(long id, Match match);

}
