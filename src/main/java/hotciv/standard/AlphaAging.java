package hotciv.standard;

import hotciv.framework.AgingStrat;
import hotciv.framework.Player;

public class AlphaAging implements AgingStrat {

    private final int WINNING_AGE = -3000;

    @Override
    public Player getWinner(WinnerStrategyContext context) {
        if(context.getAge() >= WINNING_AGE) {
            return Player.RED;
        }
        return null;
    }
    @Override
    public int increaseAge(int age) {
        return age + 100;
    }
}
