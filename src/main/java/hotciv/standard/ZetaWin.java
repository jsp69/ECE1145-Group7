package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.WinStrat;


public class ZetaWin implements WinStrat {

    private WinStrat BetaWin, EpsilonWin, currentWinner;

    public ZetaWin(WinStrat BetaWin, WinStrat EpsilonWin) {
        this.BetaWin = BetaWin;
        this.EpsilonWin = EpsilonWin;
        this.currentWinner = null;
    }

    @Override
    public Player getWinner(Game game) {
        int roundsPassed = 0;
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
}
