package com.miage.altea.tp.battle_api.battle.bo;

import org.hibernate.annotations.GenericGenerator;

import javax.annotation.Generated;
import javax.persistence.GeneratedValue;

public class Battle {

    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String uuid;

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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
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
