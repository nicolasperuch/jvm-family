package dev.peruch.camelo.service.client;


import feign.Param;
import feign.RequestLine;

public interface Dota2Client {

    @RequestLine("GET /heroes")
    String getHeroes();

    @RequestLine("GET /heroes/{heroId}/matches")
    String getHeroMatchesById(@Param("heroId") String heroId);
}
