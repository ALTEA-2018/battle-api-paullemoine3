package com.miage.altea.tp.battle_api.battle.service.battle;

import com.miage.altea.tp.battle_api.battle.bo.Battle;

import java.util.List;

public interface IBattleService {

    public Battle createBattle(String train, String oppo);
    public List<Battle> getBattles();
    public Battle getBattle(String uuid);
    public Battle attackBattle(String uuid, String attackers);
}
