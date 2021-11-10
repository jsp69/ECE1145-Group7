package hotciv.standard;

import hotciv.framework.*;

public class SemiFactory implements HotCivFactory {
    @Override
    public WorldStrat createWorldStrat() { return new DeltaWorld(); }

    @Override
    public WinStrat createWinStrat() {
        return new EpsilonWin();
    }

    @Override
    public AgingStrat createAgingStrat() {
        return new BetaAging();
    }

    @Override
    public UnitActionStrat createUnitActionStrat() {
        return new GammaUnitAction();
    }

    @Override
    public MoveAttackStrat createMoveAttackStrat() {
        return new EpsilonMoveAttack();
    }

    @Override
    public CityStrat createCityStrat() {
        return new EtaCity();
    }

    //TODO: DeltaWorld
}
