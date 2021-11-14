package hotciv.standard;

import hotciv.framework.*;

public class AlphaUnitAction implements UnitActionStrat {
    Unit[][] u;
    City[][] c;
    Tile[][] t;

    @Override
    public City[][] getCitiesArray() { return c; }

    @Override
    public Tile[][] getTilesArray() { return t; }

    @Override
    public Unit[][] getUnitsArray() { return u; }

    @Override
    public void performUnitActionAt(Position p) {
        //Update arrays
        u = GameImpl.getUnitLoc();
        c = GameImpl.getCityLoc();
        t = GameImpl.getTileLoc();

        //Get row, column, and unit
        int r = p.getRow();
        int col = p.getColumn();

        //Check if unit is null
        if (u[r][col] != null) {
            if (u[r][col].getTypeString().equals(GameConstants.ARCHER)) {
                u[r][col].setDefenses(6);
                u[r][col].setMoveCount(0);
            } else if (u[r][col].getTypeString().equals(GameConstants.SETTLER)) {
                c[r][col] = new CityImpl(u[r][col].getOwner());
                u[r][col] = null;
            }
        }
    }
}
