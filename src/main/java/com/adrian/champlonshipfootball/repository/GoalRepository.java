package com.adrian.champlonshipfootball.repository;

import com.adrian.champlonshipfootball.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal, Long> {
}
