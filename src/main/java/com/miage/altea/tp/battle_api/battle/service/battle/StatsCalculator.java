package com.miage.altea.tp.battle_api.battle.service.battle;

import org.springframework.stereotype.Service;

@Service
public class StatsCalculator implements IStatsCalculator {

    @Override
    public int calculateHp(int base, int level){
        return 10 + level + (base * (level/50));
    }

    @Override
    public int calculateState(int base, int level){
        return 5 + (base * level/50);
    }
}
