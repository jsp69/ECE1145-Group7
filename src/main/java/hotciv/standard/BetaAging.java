package hotciv.standard;

import hotciv.framework.AgingStrat;
import hotciv.framework.Player;

public class BetaAging implements AgingStrat {

    @Override
    public Player getWinner(WinnerStrategyContext context) {
        Player candidate = null;
        for(Player owner: context.getOwners()) {
            if(candidate == null)
                candidate = owner;
            if(owner!= candidate)
                return null;
        }
        return candidate;
    }

    @Override
    public int increaseAge(int age) {
        int newAge=age;
        if (age >= -4000 && age < -100) {
            newAge += 100;
        } else if (age == -100) {
            newAge = -1;
        } else if (age == -1) {
            newAge = 1;
        } else if (age == 1) {
            newAge = 50;
        } else if (age >= 50 && age < 1750) {
            newAge += 50;
        } else if (age >= 1750 && age < 1900) {
            newAge += 25;
        } else if (age >= 1900 && age < 1970) {
            newAge += 5;
        } else if (age >= 1970) {
            newAge++;
        } else {
            newAge = -4000;
        }
        return newAge;
    }
}
