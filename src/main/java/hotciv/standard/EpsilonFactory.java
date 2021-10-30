package hotciv.standard;

import hotciv.framework.*;

public class EpsilonFactory implements CivFactory {
    @Override
    public WinStrat createWinStrat() {
        return new EpsilonWin();
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
        return new EpsilonMoveAttack();
    }
}
