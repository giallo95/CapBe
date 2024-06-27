package app.gamelorevault.Controller;

import app.gamelorevault.Entity.Game;
import app.gamelorevault.Service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable Long id) {
        Optional<Game> game = gameService.getGameById(id);
        if (game.isPresent()) {
            return ResponseEntity.ok(game.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Game createGame(@Validated @RequestBody Game game) {
        return gameService.createGame(game);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable Long id, @Validated @RequestBody Game gameDetails) {
        return ResponseEntity.ok(gameService.updateGame(id, gameDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        gameService.deleteGame(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<Game> searchGames(@RequestParam String query) {
        return gameService.searchGames(query);
    }

    @GetMapping("/searchByGenre")
    public List<Game> searchGamesByGenre(@RequestParam String genre) {
        return gameService.searchGamesByGenre(genre);
    }
}