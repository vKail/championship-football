package com.adrian.champlonshipfootball.repository;

import com.adrian.champlonshipfootball.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoalRepository extends JpaRepository<Goal, Long> {
    List<Goal> findGoalByMatch(long id);
}
