package exam.sb.teamservice.controller;


import exam.sb.teamservice.domaine.team;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.*;



@Api(value = "team", description = "REST Apis related to team Entity!!!!")
@RestController
public class teamController {



    private static Map<String, List<team>> teamDB = new HashMap<String, List<team>>();

    static {
        teamDB = new HashMap<String, List<team>>();

        List<team> lst = new ArrayList<team>();
        team tm = new team("Ajax", 1);
        lst.add(tm);
        tm = new team("Arsenal", 2);
        lst.add(tm);
        tm = new team("Liverpool", 3);
        lst.add(tm);
        tm = new team("Chelsea", 5);
        lst.add(tm);
        tm = new team("Manchester", 4);
        lst.add(tm);

        teamDB.put("Europe", lst);

        lst = new ArrayList<team>();
        tm = new team("Maroc", 1);
        lst.add(tm);
        tm = new team("Tunisie", 2);
        lst.add(tm);
        tm = new team("Algérie", 3);
        lst.add(tm);
        tm = new team("Egypte", 4);
        lst.add(tm);
        tm = new team("Congo", 5);
        lst.add(tm);

        teamDB.put("Afrique", lst);

    }



    @ApiOperation(value = "Get team by id ", response = Iterable.class, tags = "getteambyid")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })

    // Endpoint pour récupérer les détails d'une équipe par son identifiant
    @GetMapping(value = "/teams/{id}")
    public team getTeamDetails(@PathVariable Integer id) {
        for (Map.Entry<String, List<team>> entry : teamDB.entrySet()) {
            // Parcourir les équipes dans chaque région
            for (team tm : entry.getValue()) {
                // Vérifier si l'ID correspond
                if (Objects.equals(tm.getid(), id)) {
                    // Retourner l'équipe si l'ID correspond
                    return tm;
                }
            }
        }
        return null;
    }
    @ApiOperation(value = "post team by id ", response = Iterable.class, tags = "addTeambyid")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @PostMapping("/teams")
    public ResponseEntity<String> addTeam(@RequestBody team newTeam) {
        // Créer une instance de la classe Random
        Random random = new Random();
        // Générer un nombre aléatoire entre 1 et 10000 (exclus)
        int randomNumber = random.nextInt(10000) + 1;
        String key = String.valueOf(randomNumber);
        teamDB.put(key, new ArrayList<>());
        // Ajouter la nouvelle équipe à la base de données simulée
        teamDB.get(key).add(newTeam);
        // Renvoyer une réponse indiquant que l'équipe a été ajoutée avec succès
        return ResponseEntity.ok("Team added successfully");
    }

    // Endpoint pour mettre à jour les informations d'une équipe par son identifiant
    @ApiOperation(value = "update team by id ", response = Iterable.class, tags = "updateTeambyid")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @PutMapping("/teams/{id}")
    public ResponseEntity<String> updateTeam(@PathVariable String id, @RequestBody team updatedTeam) {
        for (Map.Entry<String, List<team>> entry : teamDB.entrySet()) {
            // Parcourir les équipes dans chaque région
            for (team tm : entry.getValue()) {
                // Vérifier si l'ID correspond
                if (Objects.equals(String.valueOf(tm.getid()), id)) {
                    // Mettre à jour les informations de l'équipe
                    tm.setName(updatedTeam.getName());
                    tm.setid(updatedTeam.getid());
                    // Renvoyer une réponse indiquant que l'équipe a été mise à jour avec succès
                    return ResponseEntity.ok("Team updated successfully");
                }
            }
        }
        // Renvoyer une réponse 404 si aucune équipe avec l'ID spécifié n'est trouvée
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Team not found");
    }

    // Endpoint pour supprimer une équipe par son identifiant
    @ApiOperation(value = "delete team ", response = Iterable.class, tags = "deleteTeambyid")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @DeleteMapping("/teams/{id}")
    public ResponseEntity<String> deleteTeam(@PathVariable String id) {
        for (Map.Entry<String, List<team>> entry : teamDB.entrySet()) {
            // Parcourir les équipes dans chaque région
            Iterator<team> iterator = entry.getValue().iterator();
            while (iterator.hasNext()) {
                team tm = iterator.next();
                // Vérifier si l'ID correspond
                if (Objects.equals(String.valueOf(tm.getid()), id)){
                    // Supprimer l'équipe de la liste
                    iterator.remove();
                    // Renvoyer une réponse indiquant que l'équipe a été supprimée avec succès
                    return ResponseEntity.ok("Team deleted successfully");
                }
            }
        }
        // Renvoyer une réponse 404 si aucune équipe avec l'ID spécifié n'est trouvée
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Team not found");
    }



}
