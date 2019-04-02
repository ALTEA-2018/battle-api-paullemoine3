package com.miage.altea.tp.battle_api.battle.service.battle;

import com.miage.altea.tp.battle_api.battle.bo.*;
import com.miage.altea.tp.battle_api.battle.service.trainer.ITrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BattleService implements IBattleService{

    @Autowired
    ITrainerService iTrainerService;

    @Autowired
    IBattlePokemonFactory iBattlePokemonFactory;

    private List<Battle> listBattle;

    @Override
    public Battle createBattle(String train, String oppo){
        Trainer trainer = iTrainerService.getTrainer(train);
        Trainer opponent = iTrainerService.getTrainer(oppo);


        BattleTrainer bTrainer = new BattleTrainer();
        bTrainer.setName(train);
        bTrainer.setNexTurn(true);
        for(Pokemon p : trainer.getTeam()){
            PokemonType pokeType = new PokemonType();
            for(PokemonType pT : trainer.getListPk()) {
                pokeType = (pT.getId() == p.getId()) ? pT : null;
            }
            bTrainer.getTeam().add(iBattlePokemonFactory.createBattlePokemon(pokeType, p.getLevel()));
        }

        BattleTrainer bOppo = new BattleTrainer();
        bOppo.setName(oppo);
        bOppo.setNexTurn(false);


        Battle b = new Battle();
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
}
