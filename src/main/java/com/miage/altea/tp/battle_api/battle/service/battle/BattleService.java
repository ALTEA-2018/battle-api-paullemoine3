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
        BattlePokemon bTrain = getFirstPokemon(b, 0);
        BattlePokemon bOpp = getFirstPokemon(b, 1);
        if(bTrain.getSpeed() >= bOpp.getSpeed()){
            b.getTrainer().setNextTurn(true);
            b.getOpponent().setNextTurn(false);
        }else {
            b.getOpponent().setNextTurn(true);
            b.getTrainer().setNextTurn(false);
        }

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
        Battle battle;
        battle = getBattle(uuid);
        BattleTrainer battleTrainer = battle.getTrainer();
        BattleTrainer battleOpponent = battle.getOpponent();

        BattlePokemon bTrain = getFirstPokemon(battle, 0);
        BattlePokemon bOpp = getFirstPokemon(battle, 1);

        if(bTrain != null || bOpp.getType() != null) {
            if (battleTrainer.isNextTurn() && battleTrainer.getName().equals(attackers)) {
                int hpTrain =bTrain.getHp();
                int hpOpp = bOpp.getHp();
                if(bOpp.getHp() > bTrain.getAttack()){
                    bOpp.setHp(bOpp.getHp()-bTrain.getAttack());
                    battle.getTrainer().getTeam().remove(0);
                    battle.getTrainer().getTeam().add(bTrain);
                    battleOpponent.getTeam().remove(0);
                    battleOpponent.getTeam().add(bOpp);
                }else {
                    bOpp.setHp(0);
                    bOpp.setKo(true);
                    bOpp.setAlive(false);
                    battleOpponent.getTeam().remove(0);
                    battleOpponent.getTeam().add(bOpp);
                }

            } else if (battleOpponent.isNextTurn() && battleOpponent.getName().equals(attackers)) {

            }
        }
        // Trouver l'attaquant
        // trouver le premier poké // check
        // Attaquer
        // changer pv premier poke (ou état si ko et alive)
        // reset la battle
        // renvoyer la battle
        return new Battle();
    }


    public BattlePokemon getFirstPokemon(Battle battle, int role_user){
        switch (role_user){
            case 0 :
                if(!battle.getTrainer().getTeam().isEmpty()){
                    if(check(battle.getTrainer().getTeam().get(0)))
                    return battle.getTrainer().getTeam().get(0);
                }
                break;
            case 1:
                if(!battle.getOpponent().getTeam().isEmpty()){
                    if(check(battle.getOpponent().getTeam().get(0)))
                    return battle.getOpponent().getTeam().get(0);
                }
                break;
        }

        return null;
    }

    public boolean check(BattlePokemon bP) {
        if(checkKo(bP) && checkAlive(bP)){
            return true;
        }
        return false;
    }

    public boolean checkKo(BattlePokemon bP){
        if(!bP.isKo()){
            return true;
        }
        return false;
    }

    public boolean checkAlive(BattlePokemon bP){
        if(bP.isAlive()){
            return true;
        }
        return false;
    }

}
