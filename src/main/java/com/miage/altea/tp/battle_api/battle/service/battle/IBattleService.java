package com.miage.altea.tp.battle_api.battle.service.battle;

import com.miage.altea.tp.battle_api.battle.bo.Battle;
import com.miage.altea.tp.battle_api.battle.config.AttackException;

import java.util.List;
import java.util.UUID;

public interface IBattleService {

    public Battle createBattle(String train, String oppo);
    public List<Battle> getBattles();
    public Battle getBattle(UUID uuid) ;
    public Battle attackBattle(UUID uuid, String attackers) throws AttackException;
}
