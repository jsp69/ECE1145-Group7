package hotciv.framework;

import hotciv.standard.WinnerStrategyContext;

public interface WinStrat {
    int redAttack=0;
    int blueAttack=0;
    int greenAttack=0;
    int yellowAttack=0;
    int roundsPassed = 0;

    //Determine winner from conditions
   Player getWinner(Game game);

    //Increase attack count for player
    void increaseAttack(Player p);

    //Increment round count
    void incrementRound();

    //add context object
    Player getWinner(WinnerStrategyContext context, Game game);

}
