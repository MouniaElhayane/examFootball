package exam.sb.playerservice.controller;

import exam.sb.playerservice.domaine.Player;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@Api(value = "player", description = "REST Apis related to player Entity!!!!")
public class playerController {
    private static Map<String, List<Player>> PlayerDB = new HashMap<String, List<Player>>();

    static {
        PlayerDB = new HashMap<String, List<Player>>();

        List<Player> lst = new ArrayList<Player>();
        Player tm = new Player("Bukayo Saka", 1);
        lst.add(tm);
        tm = new Player("Gabriel Jesus", 2);
        lst.add(tm);
        tm = new Player("Gabriel martinelli", 3);
        lst.add(tm);
        tm = new Player("Leo Messi", 5);
        lst.add(tm);
        tm = new Player("Christiano Ronaldo", 4);
        lst.add(tm);
        tm = new Player("Achraf Hakimi", 1);
        lst.add(tm);
        tm = new Player("Kylian Mbappe", 2);
        lst.add(tm);
        tm = new Player("Neymar", 3);
        lst.add(tm);
        tm = new Player("Amine Harite", 4);
        lst.add(tm);
        tm = new Player("Ousemane Dembele", 5);
        lst.add(tm);

        PlayerDB.put("JoueurList", lst);

    }
    @ApiOperation(value = "Get player by id ", response = Iterable.class, tags = "getplayerbyid")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    // Endpoint pour récupérer les détails d'une équipe par son identifiant
    @RequestMapping(value = "/Players/{id}", method = RequestMethod.GET)
    public Player getPlayerDetails(@PathVariable Integer id) {
        for (Map.Entry<String, List<Player>> entry : PlayerDB.entrySet()) {
            // Parcourir les équipes dans chaque région
            for (Player tm : entry.getValue()) {
                // Vérifier si l'ID correspond
                if (Objects.equals(tm.getid(), id)) {
                    // Retourner l'équipe si l'ID correspond
                    return tm;
                }
            }
        }
        return null;
    }
    @ApiOperation(value = "post player ", response = Iterable.class, tags = "addplayerbyid")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @PostMapping("/Players")
    public ResponseEntity<String> addPlayer(@RequestBody Player newPlayer) {
        // Créer une instance de la classe Random
        Random random = new Random();
        // Générer un nombre aléatoire entre 1 et 10000 (exclus)
        int randomNumber = random.nextInt(10000) + 1;
        String key = String.valueOf(randomNumber);
        PlayerDB.put(key, new ArrayList<>());
        // Ajouter la nouvelle équipe à la base de données simulée
        PlayerDB.get(key).add(newPlayer);
        // Renvoyer une réponse indiquant que l'équipe a été ajoutée avec succès
        return ResponseEntity.ok("Player added successfully");
    }


    @ApiOperation(value = "update player by id ", response = Iterable.class, tags = "updateplayerbyid")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    // Endpoint pour mettre à jour les informations d'une équipe par son identifiant
    @PutMapping("/Players/{id}")
    public ResponseEntity<String> updatePlayer(@PathVariable String id, @RequestBody Player updatedPlayer) {
        for (Map.Entry<String, List<Player>> entry : PlayerDB.entrySet()) {
            // Parcourir les équipes dans chaque région
            for (Player tm : entry.getValue()) {
                // Vérifier si l'ID correspond
                if (Objects.equals(String.valueOf(tm.getid()), id)) {
                    // Mettre à jour les informations de l'équipe
                    tm.setName(updatedPlayer.getName());
                    tm.setid(updatedPlayer.getid());
                    // Renvoyer une réponse indiquant que l'équipe a été mise à jour avec succès
                    return ResponseEntity.ok("Player updated successfully");
                }
            }
        }
        // Renvoyer une réponse 404 si aucune équipe avec l'ID spécifié n'est trouvée
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Player not found");
    }

    // Endpoint pour supprimer une équipe par son identifiant
    @ApiOperation(value = "delete player by id ", response = Iterable.class, tags = "deleteplayerbyid")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @DeleteMapping("/Players/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable String id) {
        for (Map.Entry<String, List<Player>> entry : PlayerDB.entrySet()) {
            // Parcourir les équipes dans chaque région
            Iterator<Player> iterator = entry.getValue().iterator();
            while (iterator.hasNext()) {
                Player tm = iterator.next();
                // Vérifier si l'ID correspond
                if (Objects.equals(String.valueOf(tm.getid()), id)){
                    // Supprimer l'équipe de la liste
                    iterator.remove();
                    // Renvoyer une réponse indiquant que l'équipe a été supprimée avec succès
                    return ResponseEntity.ok("Player deleted successfully");
                }
            }
        }
        // Renvoyer une réponse 404 si aucune équipe avec l'ID spécifié n'est trouvée
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Player not found");
    }

}
