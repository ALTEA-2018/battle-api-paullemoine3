package com.miage.altea.tp.battle_api.battle.service.battle;

public interface IStatsCalculator {
    public int calculateHp(int base, int level);
    public int calculateStat(int base, int level);
}
