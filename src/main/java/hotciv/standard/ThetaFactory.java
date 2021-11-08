package hotciv.standard;

import hotciv.framework.*;

public class ThetaFactory implements HotCivFactory {
    @Override
    public GameStrat createGameStrat() { return new ThetaGame(); }

    @Override
    public UnitActionStrat createUnitActionStrat() {return new AlphaUnitAction();}

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
