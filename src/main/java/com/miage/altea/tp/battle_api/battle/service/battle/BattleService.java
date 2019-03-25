package com.miage.altea.tp.battle_api.battle.service.battle;

import com.miage.altea.tp.battle_api.battle.bo.Battle;
import org.springframework.stereotype.Service;

@Service
public class BattleService implements IBattleService{

    @Override
    public Battle createBattle(String trainer, String opponent){
        Battle b = new Battle();

        b.setTrainer(trainer);
        return new Battle();
    }
}
