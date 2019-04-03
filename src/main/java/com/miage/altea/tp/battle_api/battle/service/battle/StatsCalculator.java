package com.miage.altea.tp.battle_api.battle.service.battle;

import org.springframework.stereotype.Service;

@Service
public class StatsCalculator implements IStatsCalculator {

    @Override
    public int calculateHp(int base, int level){
        Double calcul =(10 + level + (base * ((Double.valueOf(level) / Double.valueOf(50)))));
        return calcul.intValue();
    }

    @Override
    public int calculateState(int base, int level){
        Double calcul = 5 + (Double.valueOf(base) * (Double.valueOf(level)/Double.valueOf(50)));
        return calcul.intValue();
    }
}
