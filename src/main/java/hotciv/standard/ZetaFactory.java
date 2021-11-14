package hotciv.standard;

import hotciv.framework.*;

public class ZetaFactory implements HotCivFactory {

    WinStrat wB, wE;
    WinStrat ws;
    int roundsPassed;

    //Constructor for use with test stubs
    public ZetaFactory(int roundsPassed, WinStrat ws){
        wB=new BetaWin();
        wE=new EpsilonWin();
        this.roundsPassed = roundsPassed;
        this.ws = ws;
    }

    @Override
    public WorldStrat createWorldStrat() { return new AlphaWorld(); }

    @Override
    public WinStrat createWinStrat() {
        return new ZetaWin(wB, wE, roundsPassed);
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
    public CityStrat createCityStrat() { return new AlphaCity(); }
}

