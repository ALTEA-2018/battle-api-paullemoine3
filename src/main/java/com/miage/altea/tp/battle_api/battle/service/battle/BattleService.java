package com.miage.altea.tp.battle_api.battle.service.battle;

import com.miage.altea.tp.battle_api.battle.bo.*;
import com.miage.altea.tp.battle_api.battle.service.pokemonType.IPokemontTypeService;
import com.miage.altea.tp.battle_api.battle.service.trainer.ITrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BattleService implements IBattleService{

    @Autowired
    ITrainerService iTrainerService;

    @Autowired
    IBattlePokemonFactory iBattlePokemonFactory;

    @Autowired
    IPokemontTypeService pokemontTypeService;

    private List<Battle> listBattle = new ArrayList<>();

    @Override
    public Battle createBattle(String train, String oppo){
        Trainer trainer = iTrainerService.getTrainer(train);
        Trainer opponent = iTrainerService.getTrainer(oppo);


        BattleTrainer bTrainer = new BattleTrainer();
        bTrainer.setName(train);
        bTrainer
                .setTeam(trainer.getTeam().parallelStream()
                        .map(pokemon -> iBattlePokemonFactory.createBattlePokemon(
                                pokemontTypeService.getPokemonId(pokemon.getPokemonType()), pokemon.getLevel()))
                        .collect(Collectors.toList()));

        BattleTrainer bOppo = new BattleTrainer();
        bOppo.setName(oppo);
        bOppo.setTeam(trainer.getTeam().parallelStream()
                        .map(pokemon -> iBattlePokemonFactory.createBattlePokemon(
                                pokemontTypeService.getPokemonId(pokemon.getPokemonType()), pokemon.getLevel()))
                        .collect(Collectors.toList()));

        Battle b = new Battle();
        b.setUuid(UUID.randomUUID());
        b.setTrainer(bTrainer);
        b.setOpponent(bOppo);

        this.listBattle.add(b);

        return b;
    }

    @Override
    public List<Battle> getBattles(){
        return listBattle;
    }

    @Override
    public Battle getBattle(String uuid){
        Optional<Battle> battle =  this.listBattle.stream().filter(b -> b.getUuid().equals(uuid)).findFirst();
        if(battle.isPresent()){
            return battle.get();
        }
        return null;
    }

    @Override
    public Battle attackBattle(String uuid, String attackers){
        Battle battle = this.listBattle.stream().filter(t -> t.getTrainer().getName().equals(attackers)).findFirst().get();
        // Trouver l'attaquant
        // trouver le premier poké
        // Attaquer
        // changer pv premier poke (ou état si ko et alive)
        // reset la battle
        // renvoyer la battle
        return new Battle();
    }
}
