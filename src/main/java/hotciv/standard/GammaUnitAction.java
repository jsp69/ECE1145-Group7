package hotciv.standard;

import hotciv.framework.Player;
import hotciv.framework.*;

public class GammaUnitAction implements UnitActionStrat {
    @Override
    public City performUnitActionAt(Position p) {

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

        cityLoc[1][1] = new CityImpl(Player.RED);
        cityLoc[4][1] = new CityImpl(Player.BLUE);
        cityLoc[3][2] = new CityImpl(Player.BLUE);

       /* //Set settler
        Position redSettler = new Position(4, 3);
        Position blueSettler = new Position(3, 4);

        //Set archer
        Position redArcher = new Position(2, 0);
        Position blueArcher = new Position(0, 2);

        // Set units
        Unit unitR1 = new UnitImpl(GameConstants.ARCHER, Player.RED);
        Unit unitR2 = new UnitImpl(GameConstants.SETTLER, Player.RED );
        Unit unitR3 = new UnitImpl(GameConstants.SETTLER, Player.RED );
        Unit unitB1 = new UnitImpl(GameConstants.ARCHER, Player.BLUE );
        Unit unitB2 = new UnitImpl(GameConstants.SETTLER, Player.BLUE);
        Unit unitB3 = new UnitImpl(GameConstants.SETTLER, Player.BLUE);*/

            //Check if red settler
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
            }
        return null;
    }
    }