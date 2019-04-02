package com.miage.altea.tp.battle_api.battle.bo;

import java.util.UUID;

public class Battle {

    private UUID uuid;

    private BattleTrainer trainer;

    private BattleTrainer opponent;

    private boolean nextTurn;


    public Battle() {
    }

    public Battle(BattleTrainer trainer, BattleTrainer opponent, boolean nextTurn) {
        this.trainer = trainer;
        this.opponent = opponent;
        this.nextTurn = nextTurn;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public BattleTrainer getTrainer() {
        return trainer;
    }

    public void setTrainer(BattleTrainer trainer) {
        this.trainer = trainer;
    }

    public BattleTrainer getOpponent() {
        return opponent;
    }

    public void setOpponent(BattleTrainer opponent) {
        this.opponent = opponent;
    }

    public boolean isNextTurn() {
        return nextTurn;
    }

    public void setNextTurn(boolean nextTurn) {
        this.nextTurn = nextTurn;
    }
}
