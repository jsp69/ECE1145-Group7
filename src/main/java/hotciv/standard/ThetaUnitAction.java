package hotciv.standard;

import hotciv.framework.*;

import java.util.Objects;

public class ThetaUnitAction implements UnitActionStrat {
    Unit[][] u;
    City[][] c;
    Tile[][] t;

    public ThetaUnitAction() {
        u = GameImpl.getUnitLoc();
        c = GameImpl.getCityLoc();
        t = GameImpl.getTileLoc();
    }

    @Override
    public City[][] getCitiesArray() { return c; }

    @Override
    public Tile[][] getTilesArray() { return t; }

    @Override
    public Unit[][] getUnitsArray() { return u; }

    @Override
    public void performUnitActionAt(Position p) {
        System.out.print("innit");

        /*//Check if red settler
        if (p ==  unitLoc[4][3]) {
            //Remove from world
            unitLoc[4][3] = null;
            //Build city with red as owner
            cityLoc[p.getRow()][p.getColumn()] = new CityImpl(Player.RED);
            return cityLoc[p.getRow()][p.getColumn()];
        }
        //Check if blue settler
        if (p == unitLoc[3][4]) {
            //Remove from world
            unitLoc[3][4] = null;
            //Build city with blue as owner
            cityLoc[p.getRow()][p.getColumn()] = new CityImpl(Player.BLUE);
            return cityLoc[p.getRow()][p.getColumn()];
        }
        //Check if red archer
        if (p == unitLoc[2][0]) {
            //Check if already fortified
            int oldDS = unitLoc[1][0].getDefensiveStrength()*2;
            if(oldDS == unitLoc[2][0].getDefensiveStrength()) {
                //Defensive strength stays same
                unitLoc[1][0].getDefensiveStrength();
            }
            else {
                //Double defensive strength
                int ds = (unitLoc[2][0].getDefensiveStrength() * 2);
                unitLoc[2][0].setDefenses(ds);
                //Archer cannot move
                unitLoc[2][0].setMoveCount(0);
            }
        }
        //Check if blue archer
        if (p == unitLoc[0][2]) {
            //Check if already fortified
            int oldDS = unitLoc[1][1].getDefensiveStrength() * 2;
            if (oldDS == unitLoc[0][2].getDefensiveStrength()) {
                //Defensive strength stays same
                unitLoc[1][1].getDefensiveStrength();
            }
            //Double defensive strength
            int ds = (unitLoc[0][2].getDefensiveStrength()) * 2;
            unitLoc[0][2].setDefenses(ds);
            //Archer cannot move
            unitLoc[0][2].setMoveCount(0);
        }*/
        //Check if ufo
        if (p.getRow() == 1 && p.getColumn() == 1) {
            //Decrease city population by 1
            ((CityImpl)c[1][1]).setSize(c[1][1].getSize() - 1);
        }

        //Check if forest
        if (Objects.equals(t[p.getRow()][p.getColumn()].getTypeString(), GameConstants.FOREST)) {
            ((TileImpl)t[p.getRow()][p.getColumn()]).setTypeString(GameConstants.PLAINS);
        }
    }
}
