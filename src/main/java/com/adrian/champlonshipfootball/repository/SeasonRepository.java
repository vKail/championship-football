package com.adrian.champlonshipfootball.repository;

import com.adrian.champlonshipfootball.model.Season;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeasonRepository extends JpaRepository<Season, Long> {
}
