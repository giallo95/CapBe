package app.gamelorevault.Repository;

import app.gamelorevault.Entity.Game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByTitleContaining(String title);
    List<Game> findByGenre(String genre);
}
