package app.gamelorevault.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;


@Data
@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String title;
    private String genre;
    private String description;
    private String imageUrl;
    private String platform;
    private Date releaseDate;
    private String developer;
    private String publisher;
    private Double rating;
    private Double price;
}