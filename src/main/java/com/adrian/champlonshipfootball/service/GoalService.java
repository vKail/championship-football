package com.adrian.champlonshipfootball.service;

import com.adrian.champlonshipfootball.dtos.GoalDto;
import com.adrian.champlonshipfootball.model.Goal;
import com.adrian.champlonshipfootball.model.Match;
import com.adrian.champlonshipfootball.model.Player;
import com.adrian.champlonshipfootball.model.Team;
import com.adrian.champlonshipfootball.repository.GoalRepository;
import com.adrian.champlonshipfootball.repository.MatchRepository;
import com.adrian.champlonshipfootball.repository.PlayerRepository;
import com.adrian.champlonshipfootball.repository.TeamRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoalService {
    private final GoalRepository goalRepository;
    private final PlayerRepository playerRepository;
    private final MatchRepository matchRepository;
    private final TeamRepository teamRepository;

    public GoalService(GoalRepository goalRepository, PlayerRepository playerRepository, MatchRepository matchRepository, TeamRepository teamRepository) {
        this.goalRepository = goalRepository;
        this.playerRepository = playerRepository;
        this.matchRepository = matchRepository;
        this.teamRepository = teamRepository;
    }
    public List<GoalDto> findAllGoals() {
        return goalRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public GoalDto saveGoal(GoalDto goalDto) {
        Goal goal = convertToEntity(goalDto);
        Goal savedGoal = goalRepository.save(goal);
        return convertToDTO(savedGoal);
    }

    public GoalDto findGoalById(long id) {
        return goalRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public List<GoalDto> getGoalsByMatch(long match) {
        return goalRepository.findGoalByMatch_MatchId(match).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public GoalDto updateGoal(long id, GoalDto goalDto) {
        Goal existingGoal = goalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Goal not found"));

        updateGoalFromDTO(existingGoal, goalDto);
        Goal updatedGoal = goalRepository.save(existingGoal);
        return convertToDTO(updatedGoal);
    }

    public void deleteGoal(long id) {
        goalRepository.deleteById(id);
    }

    public GoalDto convertToDTO(Goal goal) {
        GoalDto goalDto = new GoalDto();
        goalDto.setGoalId(goal.getGoalId());
        goalDto.setMinute(goal.getMinute());
        if (goal.getPlayer() != null) {
            goalDto.setPlayerId(goal.getPlayer().getPlayerId());
            goalDto.setPlayerName(goal.getPlayer().getFirstname() + " " + goal.getPlayer().getLastname());
        }
        if (goal.getMatch() != null) {
            goalDto.setMatchId(goal.getMatch().getMatchId());
            goalDto.setTeamId(goal.getTeam().getTeamId());
            goalDto.setTeamName(goal.getTeam().getName());
        }
        return goalDto;
    }

    public Goal convertToEntity(GoalDto goalDto) {
        Goal goal = new Goal();
        goal.setGoalId(goalDto.getGoalId());
        goal.setMinute(goalDto.getMinute());
        if (goalDto.getPlayerId() != null) {
            Player player = playerRepository.findById(goalDto.getPlayerId())
                    .orElseThrow(() -> new RuntimeException("Player not found"));
            goal.setPlayer(player);
        }
        if (goalDto.getMatchId() != null) {
            Match match = matchRepository.findById(goalDto.getMatchId())
                    .orElseThrow(() -> new RuntimeException("Match not found"));
            goal.setMatch(match);
        }
        if (goalDto.getTeamId() != null) {
            Team team = teamRepository.findById(goalDto.getTeamId())
                    .orElseThrow(() -> new RuntimeException("Team not found"));
            goal.setTeam(team);
            return goal;
        }
        return goal;
    }

    private void updateGoalFromDTO(Goal goal, GoalDto goalDto) {
        goal.setMinute(goalDto.getMinute());
        if (goalDto.getPlayerId() != null) {
            Player player = playerRepository.findById(goalDto.getPlayerId())
                    .orElseThrow(() -> new RuntimeException("Player not found"));
            goal.setPlayer(player);
        }
        if (goalDto.getMatchId() != null) {
            Match match = matchRepository.findById(goalDto.getMatchId())
                    .orElseThrow(() -> new RuntimeException("Match not found"));
            goal.setMatch(match);
        }
    }

}

