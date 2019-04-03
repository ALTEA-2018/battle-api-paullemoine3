package com.miage.altea.tp.battle_api.battle.controller;

import com.miage.altea.tp.battle_api.battle.bo.Battle;
import com.miage.altea.tp.battle_api.battle.bo.BattlePokemon;
import com.miage.altea.tp.battle_api.battle.config.AttackException;
import com.miage.altea.tp.battle_api.battle.service.battle.BattleService;
import com.miage.altea.tp.battle_api.battle.service.pokemonType.PokemonTypeService;
import com.miage.altea.tp.battle_api.battle.service.trainer.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/battles")
public class BattleController {

    @Autowired
    TrainerService trainerService;

    @Autowired
    PokemonTypeService pokemonTypeService;


    @Autowired
    BattleService battleService;

    private BattlePokemon battlePokemon;


    @GetMapping(value="/all")
    public List<Battle> listAllBattle() {
        return battleService.getBattles();
    }

    @PostMapping(value="/")
    public Battle createBattle(@RequestParam(value="trainer") String trainer, @RequestParam(value="opponent") String opponent){
        Battle b = battleService.createBattle(trainer, opponent);
        return b;
    }

    @GetMapping(value="/{uuid}")
    public Battle getBattle(@PathVariable UUID uuid) {
        return battleService.getBattles().stream().filter(x-> x.getUuid().equals(uuid)).findFirst().get();
    }

    @PostMapping(value="/{uuid}/{attacker}/attack")
    public ResponseEntity<?> createBattle(@PathVariable UUID uuid, @PathVariable String attacker){
        Battle b = new Battle();
        try {
            b = battleService.attackBattle(uuid, attacker);
        }catch (AttackException aE){
            return new ResponseEntity<Exception>(aE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Battle>(b, HttpStatus.ACCEPTED);
    }
}
