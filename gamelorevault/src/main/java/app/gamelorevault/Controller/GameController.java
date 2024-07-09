package app.gamelorevault.Controller;


import app.gamelorevault.Entity.Game;
import app.gamelorevault.Entity.Genre;
import app.gamelorevault.Service.IgdbService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api")
public class GameController {

    private final IgdbService igdbService;

    public GameController(IgdbService igdbService) {
        this.igdbService = igdbService;
    }

    @GetMapping("/games")
    public List<Game> getGames() {
        return igdbService.fetchGames();
    }

    @GetMapping("/genres")
    public List<Genre> getGenres() {
        return igdbService.fetchGenres();
    }
}