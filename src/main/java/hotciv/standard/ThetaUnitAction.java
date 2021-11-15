package hotciv.standard;

import hotciv.framework.*;

import java.util.Objects;

public class ThetaUnitAction implements UnitActionStrat {
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
                    int oldDS = 0;
                    if ((r == 1) && (col == 0)) {
                        oldDS = u[r][col].getDefensiveStrength() * 2;
                    }
                    if (oldDS == u[2][0].getDefensiveStrength()) {
                        //Defensive strength stays same
                        u[1][0].getDefensiveStrength();
                    } else {
                        //Double defensive strength
                        int ds = (u[2][0].getDefensiveStrength() * 2);
                        u[2][0].setDefenses(ds);
                        //Archer cannot move
                        u[2][0].setMoveCount(0);
                    }
                }

                //Check if blue
                else if (Objects.equals(u[r][col].getOwner(), Player.BLUE)) {
                    //Check if already fortified
                    int oldDS = 0;
                    if ((r == 1) && (col == 1)) {
                        oldDS = u[1][1].getDefensiveStrength() * 2;
                    }
                    if (oldDS == u[0][2].getDefensiveStrength()) {
                        //Defensive strength stays same
                        u[1][1].getDefensiveStrength();
                    } else {
                        //Double defensive strength
                        int ds = (u[0][2].getDefensiveStrength()) * 2;
                        u[0][2].setDefenses(ds);
                        //Archer cannot move
                        u[0][2].setMoveCount(0);
                    }
                }
            }

            //Check if ufo
            if (Objects.equals(u[r][col].getTypeString(), GameConstants.UFO)) {
                //Decrease city population by 1
                if (c[r][col] != null) {
                    ((CityImpl)(c[r][col])).setSize(c[r][col].getSize() - 1);
                    //Destroy city if population is 0
                    if (c[r][col].getSize() == 0) { c[r][col] = null; }
                }
            }

            //Check if forest
            if (Objects.equals(t[p.getRow()][p.getColumn()].getTypeString(), GameConstants.FOREST)) {
                ((TileImpl)(t[p.getRow()][p.getColumn()])).setTypeString(GameConstants.PLAINS);
            }
        }
    }
}
