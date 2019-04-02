package com.miage.altea.tp.battle_api.battle.service.battle;

import com.miage.altea.tp.battle_api.battle.bo.BattlePokemon;
import com.miage.altea.tp.battle_api.battle.bo.PokemonType;

public interface IBattlePokemonFactory {

    public BattlePokemon createBattlePokemon(PokemonType pokemonType, int level);
}
