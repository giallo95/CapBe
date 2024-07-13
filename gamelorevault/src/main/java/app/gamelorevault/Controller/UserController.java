package app.gamelorevault.Controller;


import app.gamelorevault.Dto.UserDTO;
import app.gamelorevault.Entity.User;
import app.gamelorevault.Jwt.JwtUtil;
import app.gamelorevault.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@Validated @RequestBody UserDTO userDTO) {
        User user = userDTO.toEntity();
        userService.registerUser(user);
        Map<String, String> response = new HashMap<>();
        response.put("message", "User registered successfully");
        return ResponseEntity.ok(response);
    }

    /*@PostMapping("/register")
    public ResponseEntity<String> registerUser(@Validated @RequestBody UserDTO userDTO) {
        Useruser = userDTO.toEntity();
        userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully");
    }*/

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@Validated @RequestBody User loginDetails) {
        System.out.println("Attempting to login user: " + loginDetails.getUsername());
        Optional<User> user = userService.getUserByUsername(loginDetails.getUsername());
        if (user.isPresent()) {
            System.out.println("User found: " + user.get().getUsername());
            boolean passwordMatches = userService.checkPassword(loginDetails.getPassword(), user.get().getPassword());
            System.out.println("Password matches: " + passwordMatches);
            if (passwordMatches) {
                UserDetails userDetails = userService.loadUserByUsername(loginDetails.getUsername());
                String token = jwtUtil.generateToken(userDetails);
                System.out.println("Login successful, token generated: " + token);
                Map<String, String> response = new HashMap<>();
                response.put("token", token);
                return ResponseEntity.ok(response);
            }
        }
        System.out.println("Invalid credentials for user: " + loginDetails.getUsername());
        Map<String, String> response = new HashMap<>();
        response.put("error", "Invalid credentials");
        return ResponseEntity.status(401).body(response);
    }

    /*@PostMapping("/login")
    public ResponseEntity<String> loginUser(@Validated @RequestBody User loginDetails) {
        System.out.println("Attempting tologin user: " + loginDetails.getUsername());
        Optional<User> user = userService.getUserByUsername(loginDetails.getUsername());
        if (user.isPresent()) {
            System.out.println("User found: " + user.get().getUsername());
            boolean passwordMatches = userService.checkPassword(loginDetails.getPassword(), user.get().getPassword());
            System.out.println("Password matches: " + passwordMatches);
            if (passwordMatches) {
                UserDetails userDetails = userService.loadUserByUsername(loginDetails.getUsername());
                String token = jwtUtil.generateToken(userDetails);
                System.out.println("Login successful, token generated: " + token);
                return ResponseEntity.ok(token);
            }
        }
        System.out.println("Invalid credentials for user: " + loginDetails.getUsername());
        return ResponseEntity.status(401).body("Invalid credentials");
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/username")
    public ResponseEntity<User> getUserByUsername(@RequestParam String username) {
        Optional<User> user = userService.getUserByUsername(username);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}