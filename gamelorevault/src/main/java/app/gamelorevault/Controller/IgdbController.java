package app.gamelorevault.Controller;


import app.gamelorevault.Service.IgdbService;
import com.mashape.unirest.http.JsonNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class IgdbController {

    @Autowired
    private IgdbService igdbService;

    @GetMapping("/games")
    public ResponseEntity<?> fetchGames() {
        try {
            // Chiamata al metodo del servizio IgdbService
            return ResponseEntity.ok(igdbService.fetchGames().toString());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching games: " + e.getMessage());
        }
    }

    @GetMapping("/genres")
    public ResponseEntity<?> fetchGenres() {
        try {
            // Chiamata al metodo del servizio IgdbService
            return ResponseEntity.ok(igdbService.fetchGenres().toString());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching genres: " + e.getMessage());
        }
    }


    @GetMapping("/covers")
    public ResponseEntity<?> getCovers() throws Exception {
        return  ResponseEntity.ok (igdbService.fetchCovers().toString());
    }

    @GetMapping("/screenshots")
    public ResponseEntity<?> getScreenshots() throws Exception {
        return ResponseEntity.ok (igdbService.fetchScreenshots().toString());
    }


    @GetMapping("/search")
    public ResponseEntity<?> searchGames(@RequestParam String query) {
        try {
            return ResponseEntity.ok(igdbService.searchGames(query).toString());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error searching games: " + e.getMessage());
        }
    }
}