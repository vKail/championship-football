package com.adrian.champlonshipfootball.repository;

import com.adrian.champlonshipfootball.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {
    Match updateByStatus(long id, Match match);
    String updateByResult(long id, Match match);

}
