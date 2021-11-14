package hotciv.standard;

import hotciv.framework.*;

public class AlphaFactory implements HotCivFactory {
    @Override
    public GameStrat createGameStrat() { return new AlphaGame(); }

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

    @Override
    public MoveAttackStrat createMoveAttackStrat() {
        return new AlphaMoveAttack();
    }

    @Override
    public CityStrat createCityStrat() { return new AlphaCity(); }

}
