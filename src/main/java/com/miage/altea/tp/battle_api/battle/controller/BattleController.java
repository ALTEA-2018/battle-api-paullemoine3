package com.miage.altea.tp.battle_api.battle.controller;

import com.miage.altea.tp.battle_api.battle.bo.Battle;
import com.miage.altea.tp.battle_api.battle.bo.BattlePokemon;
import com.miage.altea.tp.battle_api.battle.service.pokemonType.PokemonTypeService;
import com.miage.altea.tp.battle_api.battle.service.trainer.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BattleController {

    @Autowired
    TrainerService trainerService;

    @Autowired
    PokemonTypeService pokemonTypeService;

    private BattlePokemon battlePokemon;

    @GetMapping(value="/battles")
    public List<Battle> listAllBattle() {
        return new ArrayList<Battle>();
    }

    @PostMapping(value="/battles")
    public Battle createBattle(@RequestBody String trainer, @RequestBody String Opponent){

    }


}
