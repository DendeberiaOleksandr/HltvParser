package com.dendeberia.domain.event;

public class DoubleEliminationBracket implements Bracket {
    private SingleEliminationBracket winnersBracket;
    private SingleEliminationBracket losersBracket;

    public SingleEliminationBracket getWinnersBracket() {
        return winnersBracket;
    }

    public void setWinnersBracket(SingleEliminationBracket winnersBracket) {
        this.winnersBracket = winnersBracket;
    }

    public SingleEliminationBracket getLosersBracket() {
        return losersBracket;
    }

    public void setLosersBracket(SingleEliminationBracket losersBracket) {
        this.losersBracket = losersBracket;
    }

    @Override
    public String toString() {
        return "DoubleEliminationBracket{" +
                "winnersBracket=" + winnersBracket +
                ", losersBracket=" + losersBracket +
                '}';
    }
}
