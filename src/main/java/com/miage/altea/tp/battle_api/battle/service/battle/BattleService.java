package com.miage.altea.tp.battle_api.battle.service.battle;

import com.miage.altea.tp.battle_api.battle.bo.*;
import com.miage.altea.tp.battle_api.battle.config.AttackException;
import com.miage.altea.tp.battle_api.battle.service.pokemonType.IPokemontTypeService;
import com.miage.altea.tp.battle_api.battle.service.trainer.ITrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BattleService implements IBattleService {

    @Autowired
    ITrainerService iTrainerService;

    @Autowired
    IBattlePokemonFactory iBattlePokemonFactory;

    @Autowired
    IPokemontTypeService pokemontTypeService;

    private List<Battle> listBattle = new ArrayList<>();

    @Override
    public Battle createBattle(String train, String oppo) {
        Trainer trainer = iTrainerService.getTrainer(train);
        Trainer opponent = iTrainerService.getTrainer(oppo);


        BattleTrainer bTrainer = new BattleTrainer();
        bTrainer.setName(train);
        bTrainer
                .setTeam(trainer.getTeam().parallelStream()
                        .map(pokemon -> iBattlePokemonFactory.createBattlePokemon(
                                pokemontTypeService.getPokemonId(pokemon.getPokemonType()), pokemon.getLevel()))
                        .collect(Collectors.toList()));

        BattleTrainer bOppo = new BattleTrainer();
        bOppo.setName(oppo);
        bOppo.setTeam(opponent.getTeam().parallelStream()
                .map(pokemon -> iBattlePokemonFactory.createBattlePokemon(
                        pokemontTypeService.getPokemonId(pokemon.getPokemonType()), pokemon.getLevel()))
                .collect(Collectors.toList()));


        Battle b = new Battle();
        b.setUuid(UUID.randomUUID());
        b.setTrainer(bTrainer);
        b.setOpponent(bOppo);
        BattlePokemon bPTrain = bTrainer.getTeam().stream().filter(bP -> (!bP.isKo() && bP.isAlive())).findFirst().get();
        BattlePokemon bPOpp = bOppo.getTeam().stream().filter(bP -> (!bP.isKo() && bP.isAlive())).findFirst().get();
        if (bPTrain.getSpeed() >= bPOpp.getSpeed()) {
            b.getTrainer().setNextTurn(true);
            b.getOpponent().setNextTurn(false);
        } else {
            b.getOpponent().setNextTurn(true);
            b.getTrainer().setNextTurn(false);
        }

        this.listBattle.add(b);

        return b;
    }

    @Override
    public List<Battle> getBattles() {
        return listBattle;
    }

    @Override
    public Battle getBattle(UUID uuid) {
        Optional<Battle> battle = this.listBattle.stream().filter(b -> b.getUuid().equals(uuid)).findFirst();
        if (battle.isPresent()) {
            return battle.get();
        }
        return null;
    }

    @Override
    public Battle attackBattle(UUID uuid, String attackers) throws AttackException {
        Battle battle;
        battle = getBattle(uuid);

        BattleTrainer attacker = battle.getTrainer().getName().equals(attackers) ? battle.getTrainer() : battle.getOpponent();
        BattleTrainer opponent = !battle.getOpponent().getName().equals(attackers) ? battle.getOpponent() : battle.getTrainer();
        if (attacker.isNextTurn()) {
            BattlePokemon bAttack = attacker.getTeam().stream().filter(bP -> (!bP.isKo() && bP.isAlive())).findFirst().get();
            BattlePokemon bOppo = opponent.getTeam().stream().filter(bP -> (!bP.isKo() && bP.isAlive())).findFirst().get();
            attackPokemon(bAttack, bOppo);
            changeTurn(attacker, opponent);
        } else {
            throw new AttackException("Not your turn!");
        }

        return battle;
    }

    public void attackPokemon(BattlePokemon bPAttack, BattlePokemon bPOppo) {
        Double attack = ((Double.valueOf(2 * bPAttack.getLevel()) / Double.valueOf(5)) + 2 * (Double.valueOf(bPAttack.getAttack()) / Double.valueOf(bPOppo.getDefense())) + 2);
        if (bPOppo.getHp() - attack.intValue() > 0) {
            bPOppo.setHp(bPOppo.getHp() - attack.intValue());
        } else {
            bPOppo.setHp(0);
            bPOppo.setKo(true);
            bPOppo.setAlive(false);
        }
    }

    public void changeTurn(BattleTrainer attacker, BattleTrainer opponent){
        attacker.setNextTurn(false);
        opponent.setNextTurn(true);
    }
}
