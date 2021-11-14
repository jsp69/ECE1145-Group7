package hotciv.standard;

import hotciv.framework.*;

public class ThetaFactory implements HotCivFactory {
    @Override
    public WorldStrat createWorldStrat() { return new ThetaWorld(); }

    @Override
    public UnitActionStrat createUnitActionStrat() { return new ThetaUnitAction(); }

    @Override
    public MoveAttackStrat createMoveAttackStrat() {
        return new ThetaMoveAttack();
    }

    @Override
    public WinStrat createWinStrat() {
        return new AlphaWin();
    }

    @Override
    public AgingStrat createAgingStrat() {
        return new AlphaAging();
    }

    @Override
    public CityStrat createCityStrat() { return new AlphaCity(); }
}
