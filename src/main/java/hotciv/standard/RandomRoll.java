package hotciv.standard;

import hotciv.framework.DiceRoll;

import java.util.Random;

public class RandomRoll implements DiceRoll {
    @Override
    public int rollDice() {
        return new Random().nextInt(6)+1;
    }
}
