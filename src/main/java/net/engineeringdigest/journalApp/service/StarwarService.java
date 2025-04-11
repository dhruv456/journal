package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.DTO.Starwar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Component
public class StarwarService {
    private final String STAR_WAR_API = "https://swapi.dev/api/people/PID";

    @Autowired
    private RestTemplate restTemplate;
    public String getRandomStarwarFacts() {
        Random rand = new Random();
        int randomNum = rand.nextInt(83) + 1;
        String FinalApi = STAR_WAR_API.replace("PID", String.valueOf(randomNum));
        ResponseEntity<Starwar> response = restTemplate.exchange(FinalApi, HttpMethod.GET, null, Starwar.class);
        if(response.getStatusCode() == HttpStatus.NOT_FOUND) {
            return "unable to fetch facts for time being";
        }
        Starwar body = response.getBody();
        return "In Star War movies " + body.getName() + " have hair color " + body.getHairColor();
    }
}
