package com.adrian.champlonshipfootball.service;

import com.adrian.champlonshipfootball.model.Goal;
import com.adrian.champlonshipfootball.model.Match;
import com.adrian.champlonshipfootball.repository.GoalRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GoalService {
    private final GoalRepository goalRepository;
    public GoalService(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }
    public List<Goal> findAllGoals() {
        return goalRepository.findAll();
    }
    public Goal saveGoal(Goal goal) {
        return goalRepository.save(goal);
    }
    public Goal findGoalById(long id) {
        return goalRepository.findById(id).orElse(null);
    }
    public List<Goal> getGoalByMatch(Match match) {
        return goalRepository.findGoalByMatch(match);
    }
    public Goal updateGoal(long id,Goal goal) {
        Goal goalToUpdate = findGoalById(id);
        goalToUpdate.setPlayer(goal.getPlayer());
        goalToUpdate.setMinute(goal.getMinute());
        goalToUpdate.setMatch(goal.getMatch());
        return goalRepository.save(goalToUpdate);
    }
    public void deleteGoal(long id) {
        goalRepository.deleteById(id);
    }

}
