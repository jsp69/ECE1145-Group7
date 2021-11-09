package hotciv.standard;

import hotciv.framework.*;

public class DeltaFactory implements HotCivFactory {

    @Override
    public WorldStrat createWorldStrat() { return new DeltaWorld(); }

    @Override
    public WinStrat createWinStrat() {
        return new AlphaWin();
    }

    @Override
    public AgingStrat createAgingStrat() {
        return new AlphaAging();
    }

    @Override
    public UnitActionStrat createUnitActionStrat() {
        return new AlphaUnitAction();
    }

    @Override
    public MoveAttackStrat createMoveAttackStrat() {
        return new AlphaMoveAttack();
    }

    @Override
    public CityStrat createCityStrat() {
        return new AlphaCity();
    }
}
