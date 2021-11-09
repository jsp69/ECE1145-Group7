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
        Unit unit = u[r][col];
        System.out.print("Tile type (1): " + t[r][col].getTypeString() + "\n");

        //Check if unit is null
        if (unit != null) {
            //Check if settler
            if (Objects.equals(u[r][col].getTypeString(), GameConstants.SETTLER)) {
                Player player = u[r][col].getOwner();
                //Remove from world
                u[r][col] = null;
                //Build city with same owner
                c[r][col] = new CityImpl(player);
            }

            //Check if archer
            if (Objects.equals(u[r][col].getTypeString(), GameConstants.ARCHER)) {
                //Check if red
                if (Objects.equals(u[r][col].getOwner(), Player.RED)) {
                    //Check if already fortified
                    int oldDS = u[1][0].getDefensiveStrength()*2;
                    if (oldDS == u[2][0].getDefensiveStrength()) {
                        //Defensive strength stays same
                        u[1][0].getDefensiveStrength();
                    }
                    if (oldDS != u[2][0].getDefensiveStrength()) {
                        //Double defensive strength
                        int ds = (u[2][0].getDefensiveStrength() * 2);
                        u[2][0].setDefenses(ds);
                        //Archer cannot move
                        u[2][0].setMoveCount(0);
                    }
                }

                //Check if blue
                if (Objects.equals(u[r][col].getOwner(), Player.BLUE)) {
                    //Check if already fortified
                    int oldDS = u[1][1].getDefensiveStrength() * 2;
                    if (oldDS == u[0][2].getDefensiveStrength()) {
                        //Defensive strength stays same
                        u[1][1].getDefensiveStrength();
                    }
                    if (oldDS != u[0][2].getDefensiveStrength()) {
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
                ((CityImpl)c[1][1]).setSize(c[1][1].getSize() - 1);
            }

            //Check if forest
            if (Objects.equals(t[p.getRow()][p.getColumn()].getTypeString(), GameConstants.FOREST)) {
                ((TileImpl)t[p.getRow()][p.getColumn()]).setTypeString(GameConstants.PLAINS);
            }
        }
        System.out.print("Tile type (2): " + t[r][col].getTypeString() + "\n");
    }
}
