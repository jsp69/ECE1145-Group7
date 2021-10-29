package hotciv.standard;

import hotciv.framework.AgingStrat;
import hotciv.framework.CivFactory;
import hotciv.framework.UnitActionStrat;
import hotciv.framework.WinStrat;

public class AlphaFactory implements CivFactory {
    @Override
    public WinStrat createWinStrat() {
        return new AlphaWin();
    }

    @Override
    public AgingStrat createAgingStrat() {
        return new AlphaAging();
    }

    @Override
    public UnitActionStrat createUnitActionStrat() { return new AlphaUnitAction(); }

}
