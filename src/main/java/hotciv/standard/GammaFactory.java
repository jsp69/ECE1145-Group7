package hotciv.standard;

import hotciv.framework.AgingStrat;
import hotciv.framework.UnitActionStrat;
import hotciv.framework.CivFactory;
import hotciv.framework.WinStrat;

public class GammaFactory implements CivFactory {

    @Override
    public UnitActionStrat createUnitActionStrat() {return new GammaUnitAction();}

    @Override
    public WinStrat createWinStrat() {
        return null;
    }

    @Override
    public AgingStrat createAgingStrat() {
        return null;
    }

}

