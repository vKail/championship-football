package com.adrian.champlonshipfootball.controller;

import com.adrian.champlonshipfootball.model.Goal;
import com.adrian.champlonshipfootball.service.GoalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GoalController {
    private final GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @GetMapping("/goals")
    public List<Goal> getGoals() throws Exception {
        try {
            return goalService.findAllGoals();
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @GetMapping("/goals/{id}")
    public Goal getGoalById(@PathVariable Long id) throws Exception {
        try {
            return goalService.findGoalById(id);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @PostMapping("/goals")
    public Goal saveGoal(@RequestBody Goal goal) throws Exception {
        try {
            return goalService.saveGoal(goal);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @PutMapping("/goals/{id}")
    public Goal updateGoal(@PathVariable Long id, @RequestBody Goal goal) throws Exception {
        try {
            return goalService.updateGoal(id, goal);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/goals/{id}")
    public void deleteGoal(@PathVariable Long id) throws Exception {
        try {
            goalService.deleteGoal(id);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

}
