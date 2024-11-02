package com.adrian.champlonshipfootball.controller;

import com.adrian.champlonshipfootball.dtos.GoalDto;
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
    public List<GoalDto> getGoals() throws Exception {
        try {
            return goalService.findAllGoals();
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @GetMapping("/goals/{id}")
    public GoalDto getGoalById(@PathVariable Long id) throws Exception {
        try {
            return goalService.findGoalById(id);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @PostMapping("/goals")
    public GoalDto saveGoal(@RequestBody GoalDto goal) throws Exception {
        try {
            return goalService.saveGoal(goal);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @PutMapping("/goals/{id}")
    public GoalDto updateGoal(@PathVariable Long id, @RequestBody GoalDto goal) throws Exception {
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
