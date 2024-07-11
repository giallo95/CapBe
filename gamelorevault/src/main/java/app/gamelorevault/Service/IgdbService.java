package app.gamelorevault.Service;



import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class IgdbService {

    @Value("${igdb.api.client-id}")
    private String clientId;

    @Value("${igdb.api.access-token}")
    private String accessToken;

    public JsonNode fetchGames() throws Exception {
        String url = "https://api.igdb.com/v4/games";
        String fields = "age_ratings,aggregated_rating,aggregated_rating_count,alternative_names,artworks,bundles,category,checksum,collection,collections,cover,created_at,dlcs,expanded_games,expansions,external_games,first_release_date,follows,forks,franchise,franchises,game_engines,game_localizations,game_modes,genres,hypes,involved_companies,keywords,language_supports,multiplayer_modes,name,parent_game,platforms,player_perspectives,ports,rating,rating_count,release_dates,remakes,remasters,screenshots,similar_games,slug,standalone_expansions,status,storyline,summary,tags,themes,total_rating,total_rating_count,updated_at,url,version_parent,version_title,videos,websites;";

        HttpResponse<JsonNode> jsonResponse = Unirest.post(url)
                .header("Client-ID", clientId)
                .header("Authorization", "Bearer " + accessToken)
                .header("Accept", "application/json")
                .body("fields " + fields)
                .asJson();

        return jsonResponse.getBody();
    }

    public JsonNode fetchGenres() throws Exception {
        String url = "https://api.igdb.com/v4/genres";
        String genreFields = "checksum,created_at,name,slug,updated_at,url;";

        HttpResponse<JsonNode> jsonResponse = Unirest.post(url)
                .header("Client-ID", clientId)
                .header("Authorization", "Bearer " + accessToken)
                .header("Accept", "application/json")
                .body("fields " + genreFields)
                .asJson();
        return jsonResponse.getBody();
    }

    public JsonNode fetchCovers() throws Exception {
        HttpResponse<JsonNode> jsonResponse = Unirest.post("https://api.igdb.com/v4/covers")
                .header("Client-ID", clientId)
                .header("Authorization", accessToken)
                .header("Accept", "application/json")
                .body("fields alpha_channel,animated,checksum,game,game_localization,height,image_id,url,width;")
                .asJson();
        return jsonResponse.getBody();
    }

    public JsonNode fetchScreenshots() throws Exception {
        HttpResponse<JsonNode> jsonResponse = Unirest.post("https://api.igdb.com/v4/screenshots")
                .header("Client-ID", clientId)
                .header("Authorization", accessToken)
                .header("Accept", "application/json")
                .body("fields alpha_channel,animated,checksum,game,height,image_id,url,width;")
                .asJson();
        return jsonResponse.getBody();
    }
}