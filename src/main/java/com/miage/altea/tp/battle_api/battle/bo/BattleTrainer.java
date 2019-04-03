package com.miage.altea.tp.battle_api.battle.bo;

import java.util.List;

public class BattleTrainer {
    String name;
    List<BattlePokemon> Team;
    boolean nextTurn;

    public BattleTrainer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<BattlePokemon> getTeam() {
        return Team;
    }

    public void setTeam(List<BattlePokemon> team) {
        Team = team;
    }

    public boolean isNextTurn() {
        return nextTurn;
    }

    public void setNextTurn(boolean nextTurn) {
        this.nextTurn = nextTurn;
    }
}
