package hotciv.standard;

import hotciv.framework.*;

public class VarCivImpl implements Game {

    GameImpl base;

    public VarCivImpl(GameImpl g){this.base=g;}

    @Override
    public Tile getTileAt(Position p) {
       return base.getTileAt(p);
    }

    @Override
    public Unit getUnitAt(Position p) {
        return base.getUnitAt(p);
    }

    @Override
    public City getCityAt(Position p) {
        return base.getCityAt(p);
    }

    @Override
    public Player getPlayerInTurn() {
        return base.getPlayerInTurn();
    }

    @Override
    public Player getWinner() {
        return base.getWinner();
    }

    @Override
    public int getAge() {
        return base.getAge();
    }

    @Override
    public boolean moveUnit(Position from, Position to) {
        return base.moveUnit(from, to);
    }

    @Override
    public void endOfTurn() {
        base.endOfTurn();
    }

    @Override
    public void changeWorkForceFocusInCityAt(Position p, String balance) {
        base.changeWorkForceFocusInCityAt(p,balance);
    }

    @Override
    public void changeProductionInCityAt(Position p, String unitType) {
        base.changeProductionInCityAt(p,unitType);
    }

    @Override
    public void performUnitActionAt(Position p) {
        base.performUnitActionAt(p);
    }
}
