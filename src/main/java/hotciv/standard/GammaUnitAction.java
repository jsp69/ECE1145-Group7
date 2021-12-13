package hotciv.standard;

import hotciv.framework.*;

import java.util.Objects;

public class GammaUnitAction implements UnitActionStrat {
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

        //Get row, column
        int r = p.getRow();
        int col = p.getColumn();

        //Check if unit is null
        if (u[r][col] != null) {
            //Check if settler
            if (Objects.equals(u[r][col].getTypeString(), GameConstants.SETTLER)) {
                Player player = u[r][col].getOwner();
                //Remove from world
                u[r][col] = null;
                //Build city with same owner
                c[r][col] = new CityImpl(player);
            }

            //Check if archer
            else if (Objects.equals(u[r][col].getTypeString(), GameConstants.ARCHER)) {
                //Check if red
                if (Objects.equals(u[r][col].getOwner(), Player.RED)) {
                    //Check if already fortified
                    int oldDS = u[r][col].getDefensiveStrength();
                    if(oldDS % 2 == 0) {
                        //Defensive strength stays same
                        u[r][col].getDefensiveStrength();
                    } else {
                        //Double defensive strength
                        int ds = (u[r][col].getDefensiveStrength() * 2);
                        ((UnitImpl)(u[r][col])).setDefenses(ds);
                        //Archer cannot move
                        ((UnitImpl)(u[r][col])).setMoveCount(0);
                    }
                }

                //Check if blue
                else if (Objects.equals(u[r][col].getOwner(), Player.BLUE)) {
                    //Check if already fortified
                    int oldDS = u[r][col].getDefensiveStrength();
                    if(oldDS % 2 == 0) {
                        //Defensive strength stays same
                        u[r][col].getDefensiveStrength();
                    } else {
                        //Double defensive strength
                        int ds = (u[r][col].getDefensiveStrength()) * 2;
                        ((UnitImpl)(u[r][col])).setDefenses(ds);
                        //Archer cannot move
                        ((UnitImpl)(u[r][col])).setMoveCount(0);
                    }
                }
            }
        }
    }
}