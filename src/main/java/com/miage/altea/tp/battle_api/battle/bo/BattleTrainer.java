package com.miage.altea.tp.battle_api.battle.bo;

import java.util.List;

public class BattleTrainer {
    String name;
    boolean nexTurn;
    List<BattlePokemon> Team;

    public BattleTrainer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isNexTurn() {
        return nexTurn;
    }

    public void setNexTurn(boolean nexTurn) {
        this.nexTurn = nexTurn;
    }

    public List<BattlePokemon> getTeam() {
        return Team;
    }

    public void setTeam(List<BattlePokemon> team) {
        Team = team;
    }
}
