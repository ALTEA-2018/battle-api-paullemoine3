package com.miage.altea.tp.battle_api.battle.controller;

import com.miage.altea.tp.battle_api.battle.bo.Battle;
import com.miage.altea.tp.battle_api.battle.bo.BattlePokemon;
import com.miage.altea.tp.battle_api.battle.service.battle.BattleService;
import com.miage.altea.tp.battle_api.battle.service.pokemonType.PokemonTypeService;
import com.miage.altea.tp.battle_api.battle.service.trainer.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BattleController {

    @Autowired
    TrainerService trainerService;

    @Autowired
    PokemonTypeService pokemonTypeService;


    @Autowired
    BattleService battleService;

    private BattlePokemon battlePokemon;


    @GetMapping(value="/battles")
    public List<Battle> listAllBattle() {
        return battleService.getBattles();
    }

    @PostMapping(value="/battles")
    public String createBattle(@RequestParam String trainer, @RequestParam String opponent){
        Battle b = battleService.createBattle(trainer, opponent);
        return b.getUuid();
    }

    @PostMapping(value="/battles/{battle_uuid}/{nameTrainer}/attack")
    public


}
