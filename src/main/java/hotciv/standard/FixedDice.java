package hotciv.standard;

import hotciv.framework.DiceRoll;

public class FixedDice implements DiceRoll {
    int roll;

    public FixedDice(int i){roll=i;}

    @Override
    public int rollDice() {return roll;}
}
