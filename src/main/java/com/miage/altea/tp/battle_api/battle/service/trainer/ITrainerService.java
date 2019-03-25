package com.miage.altea.tp.battle_api.battle.service.trainer;

import com.miage.altea.tp.battle_api.battle.bo.Trainer;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public interface ITrainerService {

    public List<Trainer> listTrainers();
    public Trainer getTrainer(String name);
    public void setRestTemplate(RestTemplate restTemplate);
    public void setTrainerServiceUrl(String trainerService);
}
