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
    public List<Goal> getGoals() {
        return goalService.findAllGoals();
    }

    @GetMapping("/goals/{id}")
    public Goal getGoalById(@PathVariable Long id) {
        return goalService.findGoalById(id);
    }

    @PostMapping("/goals")
    public Goal saveGoal(@RequestBody Goal goal) {
        return goalService.saveGoal(goal);
    }

    @PutMapping("/goals/{id}")
    public Goal updateGoal(@PathVariable Long id, @RequestBody Goal goal) {
        return goalService.updateGoal(id, goal);
    }

    @DeleteMapping("/goals/{id}")
    public void deleteGoal(@PathVariable Long id) {
        goalService.deleteGoal(id);
    }
}
