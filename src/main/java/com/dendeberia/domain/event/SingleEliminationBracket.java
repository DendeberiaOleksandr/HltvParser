package com.dendeberia.domain.event;

import java.util.List;

public class SingleEliminationBracket implements Bracket {
    private List<BracketRound> rounds;

    public List<BracketRound> getRounds() {
        return rounds;
    }

    public void setRounds(List<BracketRound> rounds) {
        this.rounds = rounds;
    }

    @Override
    public String toString() {
        return "SingleEliminationBracket{" +
                "rounds=" + rounds +
                '}';
    }
}
