package com.adrian.champlonshipfootball.service;

import com.adrian.champlonshipfootball.model.Goal;
import com.adrian.champlonshipfootball.repository.GoalRepository;

import java.util.List;

public class GoalService {
    GoalRepository goalRepository;
    public GoalService(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }
    public List<Goal> getAllGoals() {
        return goalRepository.findAll();
    }
    public Goal saveGoal(Goal goal) {
        return goalRepository.save(goal);
    }
    public Goal getGoalById(long id) {
        return goalRepository.findById(id).orElse(null);
    }
    public List<Goal> getGoalByMatch(long id) {
        return goalRepository.findGoalByMatch(id);
    }
    public Goal updateGoal(long id,Goal goal) {
        Goal goalToUpdate = getGoalById(id);
        goalToUpdate.setPlayer(goal.getPlayer());
        goalToUpdate.setMinute(goal.getMinute());
        goalToUpdate.setMatch(goal.getMatch());
        return goalRepository.save(goalToUpdate);
    }
    public void deleteGoal(long id) {
        goalRepository.deleteById(id);
    }

}
