package com.miage.altea.tp.battle_api.battle.service.pokemonType;

import com.miage.altea.tp.battle_api.battle.bo.PokemonType;

import java.util.List;

public interface IPokemontTypeService {

    public List<PokemonType> listPokemonsTypes();
    public PokemonType getPokemonId(int id);
    public void setPokemonTypeServiceUrl(String pokemonServiceUrl);
    public PokemonType getPokemonType(int id);
    public List<PokemonType> getVoidlistPokemons();
}
