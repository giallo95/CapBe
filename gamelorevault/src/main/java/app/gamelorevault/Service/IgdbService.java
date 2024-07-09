package app.gamelorevault.Service;


import app.gamelorevault.Entity.Game;
import app.gamelorevault.Entity.Genre;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class IgdbService {

    @Value("${igdb.api.url}")
    private String apiUrl;

    @Value("${igdb.api.client-id}")
    private String clientId;

    @Value("${igdb.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public IgdbService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Game> fetchGames() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Client-ID", clientId);
        headers.set("Authorization", "Bearer " + apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<List<Game>> response = restTemplate.exchange(
                apiUrl + "/games",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Game>>() {}
        );

        return response.getBody();
    }

    public List<Genre> fetchGenres() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Client-ID", clientId);
        headers.set("Authorization", "Bearer " + apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<List<Genre>> response = restTemplate.exchange(
                apiUrl + "/genres",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Genre>>() {}
        );

        return response.getBody();
    }
}