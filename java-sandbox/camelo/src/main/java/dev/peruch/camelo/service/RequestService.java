package dev.peruch.camelo.service;

import dev.peruch.camelo.service.client.Dota2Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestService {

    @Autowired
    private Dota2Client dota2Client;

    public Object getHeroes(){
        return dota2Client.getHeroes();
    }

    public Object getHeroMatchesById(String id) {
        return dota2Client.getHeroMatchesById(id);
    }
}
