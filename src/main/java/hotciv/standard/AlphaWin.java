package hotciv.standard;

import hotciv.framework.*;

public class AlphaWin implements WinStrat {
    @Override
    public Player getWinner(Game game) {
        //Red wins if 3000 BC
        if (game.getAge() == -3000) {
            return Player.RED;
        }else{
            return null;
        }
    }

    @Override
    public void increaseAttack(Player p) {

    }

    @Override
    public void incrementRound() {

    }

    @Override
    public Player getWinner(WinnerStrategyContext context) {
        return null;
    }
}
