package com.miage.altea.tp.battle_api.battle.service.battle;

import com.miage.altea.tp.battle_api.battle.bo.Battle;
import com.miage.altea.tp.battle_api.battle.bo.BattlePokemon;
import com.miage.altea.tp.battle_api.battle.bo.PokemonType;
import com.miage.altea.tp.battle_api.battle.bo.Trainer;
import com.miage.altea.tp.battle_api.battle.controller.SecurityControllerAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BattlePokemonFactory {

    @Autowired
    BattleService battleService;

    @Autowired
    SecurityControllerAdvice securityControllerAdvice;

    public BattlePokemon createBattle(PokemonType pokemonType, int level){
        Battle b = battleService.createBattle();
        Trainer t = (Trainer) securityControllerAdvice.principal();
        b.setTrainer(t);
        b.set

        BattlePokemon battlePokemon;
        battlePokemon.setHp();
        battlePokemon.setMaxHp();
        battlePokemon.setType();

        return
    }
}
