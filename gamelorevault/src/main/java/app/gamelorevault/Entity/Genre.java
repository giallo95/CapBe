package app.gamelorevault.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Genre {
    @Id
    private Long id;
    private String name;
}
