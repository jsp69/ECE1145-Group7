package hotciv.standard;

import hotciv.framework.*;

public class ThetaUnitAction implements UnitActionStrat {
    @Override
    public void performUnitActionAt(Position p) {
        System.out.print("innit");
        Unit[][] unitLoc = new UnitImpl[16][16];

        City [][] cityLoc = new CityImpl[16][16];

        unitLoc[2][0] = new UnitImpl(GameConstants.ARCHER, Player.RED);
        unitLoc[4][3] = new UnitImpl(GameConstants.SETTLER, Player.RED);
        unitLoc[2][3] = new UnitImpl(GameConstants.SETTLER, Player.RED);
        unitLoc[3][3] = new UnitImpl(GameConstants.LEGION, Player.RED);
        unitLoc[0][2] = new UnitImpl(GameConstants.ARCHER, Player.BLUE);
        unitLoc[3][4] = new UnitImpl(GameConstants.SETTLER, Player.BLUE);
        unitLoc[3][2] = new UnitImpl(GameConstants.LEGION, Player.BLUE);
        unitLoc[4][2] = new UnitImpl(GameConstants.LEGION, Player.BLUE);
        unitLoc[5][2] = new UnitImpl(GameConstants.LEGION, Player.BLUE);
        unitLoc[2][1] = new UnitImpl(GameConstants.UFO, Player.RED);

        cityLoc[1][1] = new CityImpl(Player.RED);
        cityLoc[4][1] = new CityImpl(Player.BLUE);
        cityLoc[3][2] = new CityImpl(Player.BLUE);

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
        if (p.getRow() == 2 && p.getColumn() == 1) {
            //Decrease city population by 1
            ((CityImpl)cityLoc[1][1]).setSize(cityLoc[1][1].getSize() - 1);
        }

        //return null;
    }
}
