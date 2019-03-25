package com.miage.altea.tp.battle_api.battle.service.battle;

public class StatsCalculator {

    public int calculateHp(int base, int level){
        return 10 + level + (base * (level/50));
    }

    public int calculateState(int base, int level){
        return 5 + (base * level/50);
    }
}
