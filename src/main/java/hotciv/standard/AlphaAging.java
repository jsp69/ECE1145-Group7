package hotciv.standard;

import hotciv.framework.AgingStrat;

public class AlphaAging implements AgingStrat {
    @Override
    public int increaseAge(int age) {
        return age + 100;
    }
}
