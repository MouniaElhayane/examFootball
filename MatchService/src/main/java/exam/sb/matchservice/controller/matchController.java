package exam.sb.matchservice.controller;

import exam.sb.matchservice.domaine.Match;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Api(value = "match", description = "REST Apis related to match Entity!!!!")
public class matchController {

    private final Map<Integer, Match> matchDB = new HashMap<>();
    private int matchCounter = 1; // Initialiser le compteur

    public matchController() {
        // Ajout de quelques matches à la liste pour l'exemple
        addMatch(new Match(1, "ÉquipeA", "ÉquipeB", 2, 1, null, "StadeA"));
        addMatch(new Match(2, "ÉquipeC", "ÉquipeD", 0, 0, null, "StadeB"));
    }

    @ApiOperation(value = "add match by id ", response = Iterable.class, tags = "addmatchbyid")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @PostMapping
    public ResponseEntity<String> addMatch(@RequestBody Match newMatch) {
        // Affecter l'ID du match avec la valeur actuelle du compteur, puis incrémenter le compteur
        newMatch.setId(matchCounter++);
        matchDB.put(newMatch.getId(), newMatch);
        return ResponseEntity.ok("Match added successfully with ID: " + newMatch.getId());
    }

    @ApiOperation(value = "get match by id ", response = Iterable.class, tags = "getmatchbyid")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @RequestMapping(value = "matches/{id}", method = RequestMethod.GET)
    public ResponseEntity<Match> getMatchById(@PathVariable Integer id) {
        Match match = matchDB.get(id);
        if (match != null) {
            return ResponseEntity.ok(match);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @ApiOperation(value = "update match by id ", response = Iterable.class, tags = "updatematchbyid")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @PutMapping("matches/{id}")
    public ResponseEntity<String> updateMatch(@PathVariable Integer id, @RequestBody Match updatedMatch) {
        if (matchDB.containsKey(id)) {
            updatedMatch.setId(id);
            matchDB.put(id, updatedMatch);
            return ResponseEntity.ok("Match updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @ApiOperation(value = "delete match by id ", response = Iterable.class, tags = "deletematchbyid")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @DeleteMapping("matches/{id}")
    public ResponseEntity<String> deleteMatch(@PathVariable Integer id) {
        if (matchDB.containsKey(id)) {
            matchDB.remove(id);
            return ResponseEntity.ok("Match deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
