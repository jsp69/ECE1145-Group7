package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.WinStrat;

import java.util.Objects;

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
}
