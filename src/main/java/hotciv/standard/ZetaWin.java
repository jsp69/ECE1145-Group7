package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.WinStrat;

import java.util.*;

public class ZetaWin implements WinStrat {

    private WinStrat BetaWin, EpsilonWin, currentWinner;
    int roundsPassed;

    public ZetaWin(WinStrat BetaWin, WinStrat EpsilonWin, int roundsPassed) {
        this.BetaWin = BetaWin;
        this.EpsilonWin = EpsilonWin;
        this.roundsPassed = roundsPassed;
        this.currentWinner = BetaWin;
    }

    @Override
    public Player getWinner(Game game) {
        if (roundsPassed < 20) {
            currentWinner = BetaWin;
        }
        else {
            currentWinner = EpsilonWin;
        }
        return currentWinner.getWinner(game);
    }

    @Override
    public void increaseAttack(Player p) {
    }

    @Override
    public void incrementRound() {
    }

    @Override
    public Player getWinner(WinnerStrategyContext context, Game game) {
        return getWinner(game);
    }
}
