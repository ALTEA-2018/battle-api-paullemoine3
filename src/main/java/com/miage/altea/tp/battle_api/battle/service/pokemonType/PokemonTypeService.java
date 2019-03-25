package com.miage.altea.tp.battle_api.battle.service.pokemonType;

import com.miage.altea.tp.battle_api.battle.bo.PokemonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PokemonTypeService  implements IPokemontTypeService{


    @Value("${pokemonType.service.url}")
    String pokemonServiceUrl;

    String base_url = "/pokemon-types/";

    RestTemplate rT;

    @Override
    @Cacheable(value = "pokemon-types")
    public List<PokemonType> listPokemonsTypes() {
        var headers = new HttpHeaders();
        headers.setAcceptLanguageAsLocales(List.of(LocaleContextHolder.getLocale()));
        var httpRequest = new HttpEntity<>(headers);
        PokemonType[] listPoke;
        listPoke = rT.getForObject(this.pokemonServiceUrl+this.base_url, PokemonType[].class, httpRequest);
        List<PokemonType> listPT = Arrays.asList(listPoke);
        return listPT;
    }

    @Override
    public PokemonType getPokemonId(int id) {
        var headers = new HttpHeaders();
        headers.setAcceptLanguageAsLocales(List.of(LocaleContextHolder.getLocale()));
        var httpRequest = new HttpEntity<>(headers);
        PokemonType pokeType;
        pokeType = rT.getForObject(this.pokemonServiceUrl+this.base_url+id, PokemonType.class, httpRequest);
        return pokeType;
    }


    @Override
    @Value("${pokemonType.service.url}")
    public void setPokemonTypeServiceUrl(String pokemonServiceUrl) {
        this.pokemonServiceUrl= pokemonServiceUrl;
    }

    @Override
    @Cacheable(value = "pokemon-types")
    public PokemonType getPokemonType(int id) {
        PokemonType pokeType;
        pokeType = rT.getForObject(this.pokemonServiceUrl+this.base_url+"{id}", PokemonType.class, id);
        return pokeType;
    }

    @Override
    public List<PokemonType> getVoidlistPokemons(){
        List<PokemonType> listPT = new ArrayList<>();
        return listPT;
    }
}
