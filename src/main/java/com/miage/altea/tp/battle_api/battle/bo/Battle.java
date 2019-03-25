package com.miage.altea.tp.battle_api.battle.bo;

public class Battle {

    private int uuid;

    private Trainer trainer;

    private Trainer opponent;

    private boolean nextTurn;


    public Battle() {
    }

    public Battle(Trainer trainer, Trainer opponent, boolean nextTurn) {
        this.trainer = trainer;
        this.opponent = opponent;
        this.nextTurn = nextTurn;
    }

    public int getUuid() {
        return uuid;
    }

    public void setUuid(int uuid) {
        this.uuid = uuid;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public Trainer getOpponent() {
        return opponent;
    }

    public void setOpponent(Trainer opponent) {
        this.opponent = opponent;
    }

    public boolean isNextTurn() {
        return nextTurn;
    }

    public void setNextTurn(boolean nextTurn) {
        this.nextTurn = nextTurn;
    }
}
