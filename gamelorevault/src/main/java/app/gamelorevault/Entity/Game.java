package app.gamelorevault.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class Game {
    @Id
    private Long id;
    private String name;
    private Cover cover;
    private List<Screenshot> screenshots;
    private List<Long> genres;

    public static class Cover {
        private String image_id;


    }

    public static class Screenshot {
        private String image_id;


    }

}