package app.gamelorevault.Service;

import app.gamelorevault.Entity.Game;
import app.gamelorevault.Repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public List<Game> getAllGames() {
        return (List<Game>) gameRepository.findAll();
    }

    public Optional<Game> getGameById(Long id) {
        return gameRepository.findById(id);
    }

    public Game createGame(Game game) {
        return gameRepository.save(game);
    }

    public Game updateGame(Long id, Game gameDetails) {
        Game game = gameRepository.findById(id).orElseThrow(() -> new RuntimeException("Game not found"));
        game.setTitle(gameDetails.getTitle());
        game.setGenre(gameDetails.getGenre());
        game.setDescription(gameDetails.getDescription());
        game.setImageUrl(gameDetails.getImageUrl());  // Imposta il campo dell'immagine
        return gameRepository.save(game);
    }

    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }

    public List<Game> searchGames(String query) {
        return gameRepository.findByTitleContaining(query);
    }

    public List<Game> searchGamesByGenre(String genre) {
        return gameRepository.findByGenre(genre);
    }
}