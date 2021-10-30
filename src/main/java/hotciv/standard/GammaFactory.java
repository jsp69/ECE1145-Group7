package hotciv.standard;

import hotciv.framework.*;

public class GammaFactory implements CivFactory {

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

}

