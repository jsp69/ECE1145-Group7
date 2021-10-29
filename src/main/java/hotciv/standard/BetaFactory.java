package hotciv.standard;

import hotciv.framework.AgingStrat;
import hotciv.framework.CivFactory;
import hotciv.framework.UnitActionStrat;
import hotciv.framework.WinStrat;

public class BetaFactory implements CivFactory {
    @Override
    public WinStrat createWinStrat() {
        return new BetaWin();
    }

    @Override
    public AgingStrat createAgingStrat() {
        return new BetaAging();
    }

    @Override
    public UnitActionStrat createUnitActionStrat() {
        return null;
    }
}
