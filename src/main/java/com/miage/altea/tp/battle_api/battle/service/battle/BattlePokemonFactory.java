package com.miage.altea.tp.battle_api.battle.service.battle;

import com.miage.altea.tp.battle_api.battle.bo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BattlePokemonFactory implements IBattlePokemonFactory{

    @Autowired
    IStatsCalculator statsCalculator;

    public BattlePokemon createBattlePokemon(PokemonType pokemonType, int level){
        BattlePokemon battlePoke = new BattlePokemon();
        battlePoke.setId(pokemonType.getId());
        battlePoke.setHp(statsCalculator.calculateHp(pokemonType.getStats().getHp(), level));
        battlePoke.setMaxHp(statsCalculator.calculateHp(pokemonType.getStats().getHp(), level));
        battlePoke.setAttack(statsCalculator.calculateState(pokemonType.getStats().getAttack(), level));
        battlePoke.setDefense(statsCalculator.calculateState(pokemonType.getStats().getDefense(), level));
        battlePoke.setLevel(level);
        battlePoke.setSpeed(statsCalculator.calculateState(pokemonType.getStats().getSpeed(), level));
        battlePoke.setKo(false);
        battlePoke.setAlive(true);
        battlePoke.setType(pokemonType);
        return battlePoke;
    }
}
