package hotciv.standard;

import hotciv.framework.*;

public class EpsilonFactory implements CivFactory {
    DiceRoll diceA,diceD;

    //Base constructor for normal use
    public EpsilonFactory(){
        diceD=new RandomRoll();
        diceA=new RandomRoll();
    }

    //Overloaded constructor for use with test stubs
    public EpsilonFactory(DiceRoll a,DiceRoll d){
        diceD=d;
        diceA=a;
    }

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
        return new EpsilonMoveAttack(diceA,diceD);
    }
}
