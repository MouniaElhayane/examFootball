package com.example.statsservice.controller;

import com.example.statsservice.domaine.PlayerStats;
import com.example.statsservice.domaine.TeamStats;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(value = "stats", description = "REST Apis related to stats Entity!!!!")
@RestController
public class statsController {

    public static final Map<String, TeamStats> teamStatsDB = new HashMap<>();
    public static final Map<String, PlayerStats> playerStatsDB = new HashMap<>();

    static {
        // Exemples de données pour les statistiques des équipes
        teamStatsDB.put("team1", new TeamStats("team1", 10, 6, 3, 1));
        teamStatsDB.put("team2", new TeamStats("team2", 8, 4, 2, 2));
        teamStatsDB.put("team3", new TeamStats("team3", 12, 8, 2, 2));

        // Exemples de données pour les statistiques des joueurs
        playerStatsDB.put("player1", new PlayerStats("player1", 5, 3, 2, 0));
        playerStatsDB.put("player2", new PlayerStats("player2", 8, 2, 5, 0));
        playerStatsDB.put("player3", new PlayerStats("player3", 10, 6, 1, 1));
    }



    @ApiOperation(value = "Get stats by id ", response = Iterable.class, tags = "statteambyid")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @RequestMapping(value = "/teamStats/{teamId}", method = RequestMethod.GET)
    public ResponseEntity<TeamStats> getTeamStats(@PathVariable String teamId) {
        TeamStats teamStats = teamStatsDB.get(teamId);

        if (teamStats != null) {
            return ResponseEntity.ok(teamStats);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/playerStats/{playerId}")
    public ResponseEntity<PlayerStats> getPlayerStats(@PathVariable String playerId) {
        PlayerStats playerStats = playerStatsDB.get(playerId);

        if (playerStats != null) {
            return ResponseEntity.ok(playerStats);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
