package app.gamelorevault.Dto;

import app.gamelorevault.Entity.User;

public class UserDTO {

    private Long id;
    private String username;
    private String password;

    public UserDTO() {
    }

    public UserDTO(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public User toEntity() {
        User user = new User();
        user.setId(this.id);
        user.setUsername(this.username);
        user.setPassword(this.password); // Assicurati di criptare la password prima di chiamare questo metodo
        return user;
    }

    public static UserDTO fromEntity(User user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getPassword());
    }
}