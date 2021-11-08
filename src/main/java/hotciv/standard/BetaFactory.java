package hotciv.standard;

import hotciv.framework.*;

public class BetaFactory implements HotCivFactory {
    @Override
    public GameStrat createGameStrat() { return new AlphaGame(); }

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
        return new AlphaUnitAction();
    }

    @Override
    public MoveAttackStrat createMoveAttackStrat() {
        return new AlphaMoveAttack();
    }

    @Override
    public CityStrat createCityStrat() { return new AlphaCity(); }
}
