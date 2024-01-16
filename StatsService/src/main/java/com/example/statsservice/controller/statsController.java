
package com.example.statsservice.controller;

import com.example.statsservice.domaine.PlayerStats;
import com.example.statsservice.domaine.TeamStats;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.util.HashMap;
import java.util.Map;

@Api(value = "stats", description = "REST Apis related to stats Entity!!!!")
@RestController
public class statsController {
    @Autowired
    RestTemplate restTemplate;

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


    //@HystrixCommand(fallbackMethod = "callStatServiceAndGetData_Fallback")
    // en ajoutant Hystrix le mapping marche plus
    @ApiOperation(value = "Get stats by id ", response = Iterable.class, tags = "statteambyid")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })

    @RequestMapping(value = "/teamStats/{teamId}", method = RequestMethod.GET)

    public ResponseEntity<String> getTeamStats(@PathVariable String teamId) {
        String response = this.restTemplate
                .exchange("http://localhost:8083/teams/{teamId}"
                        , HttpMethod.GET
                        , null
                        , new ParameterizedTypeReference<String>() {
                        }, teamId).getBody();

        return ResponseEntity.ok(response);
    }

   // @HystrixCommand(fallbackMethod = "callStatServiceAndGetData_Fallback")
    // en ajoutant Hystrix le mapping marche plus
    @GetMapping("/playerStats/{playerId}")
    public ResponseEntity<String> getPlayerStats(@PathVariable String playerId) {
        String response = this.restTemplate
                .exchange("http://localhost:8081/Players/{playerId}"
                        , HttpMethod.GET
                        , null
                        , new ParameterizedTypeReference<String>() {
                        }, playerId).getBody();

        return ResponseEntity.ok(response);
    }


    private String callStatServiceAndGetData_Fallback(String teamId) {
        System.out.println("Student Service is down!!! fallback route enabled...");
        return "CIRCUIT BREAKER ENABLED!!!No Response From Student Service at this moment. Service will be back shortly - ";
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}