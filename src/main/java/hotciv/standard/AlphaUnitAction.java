package hotciv.standard;

import hotciv.framework.*;

public class AlphaUnitAction implements UnitActionStrat {

    @Override
    public void performUnitActionAt(Position p, Game game) {
        if(game.getUnitAt(p).getTypeString().equals(GameConstants.ARCHER)){
            game.getUnitAt(p).setDefenses(6);
            game.getUnitAt(p).setMoveCount(0);
        }else if(game.getUnitAt(p).getTypeString().equals(GameConstants.SETTLER)){
            ((GameImpl)(game)).cityLoc[p.getRow()][p.getColumn()]=new CityImpl(game.getUnitAt(p).getOwner());
            ((GameImpl)(game)).unitLoc[p.getRow()][p.getColumn()]=null;
        }
    }

    @Override
    public City[][] getCitiesArray() {
        return new City[0][];
    }

    @Override
    public Tile[][] getTilesArray() {
        return new Tile[0][];
    }

    @Override
    public Unit[][] getUnitsArray() {
        return new Unit[0][];
    }
}
