package com.miage.altea.tp.battle_api.battle.service.battle;

import com.miage.altea.tp.battle_api.battle.bo.*;
import com.miage.altea.tp.battle_api.battle.controller.SecurityControllerAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BattlePokemonFactory implements IBattlePokemonFactory{

    @Autowired
    IStatsCalculator statsCalculator;

    @Autowired
    SecurityControllerAdvice securityControllerAdvice;

    public BattlePokemon createBattlePokemon(PokemonType pokemonType, int level){
        Stats s = new Stats();
        s.setAttack(statsCalculator.calculateState(pokemonType.getBaseExperience(), level));
        s.setDefense(statsCalculator.calculateState(pokemonType.getBaseExperience(), level));
        s.setSpeed(statsCalculator.calculateState(pokemonType.getBaseExperience(), level));
        s.setHp(statsCalculator.calculateHp(pokemonType.getBaseExperience(),level));

        pokemonType.setStats(s);


        BattlePokemon battlePoke = new BattlePokemon();
        battlePoke.setHp(pokemonType.getStats().getHp());
        battlePoke.setMaxHp(pokemonType.getStats().getHp());
        battlePoke.setType(pokemonType);
        return new BattlePokemon();
    }
}
