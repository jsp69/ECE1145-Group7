package hotciv.standard;

import hotciv.framework.*;

public class GammaFactory implements HotCivFactory {
    @Override
    public GameStrat createGameStrat() { return new AlphaGame(); }

    @Override
    public UnitActionStrat createUnitActionStrat() {return new GammaUnitAction();}

    @Override
    public MoveAttackStrat createMoveAttackStrat() {
        return new AlphaMoveAttack();
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

