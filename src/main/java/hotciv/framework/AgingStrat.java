package hotciv.framework;

import hotciv.standard.WinnerStrategyContext;

public interface AgingStrat {

    Player getWinner(WinnerStrategyContext context);

    //Increase Age based on algorithm
    int increaseAge(int age);
}
