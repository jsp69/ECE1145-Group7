package hotciv.standard;

import hotciv.framework.*;

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
        return new AlphaUnitAction();
    }

    @Override
    public MoveAttackStrat createMoveAttackStrat() {
        return new AlphaMoveAttack();
    }
}