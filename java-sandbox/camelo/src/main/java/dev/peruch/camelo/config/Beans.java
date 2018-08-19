package dev.peruch.camelo.config;

import dev.peruch.camelo.service.client.Dota2Client;
import feign.Feign;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }

    @Bean
    public Dota2Client dota2Client() {
        return Feign
                .builder()
                .target(Dota2Client.class, "https://api.opendota.com/api");
    }

}
