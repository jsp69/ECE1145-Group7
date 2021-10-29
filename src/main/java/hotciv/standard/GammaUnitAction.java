package hotciv.standard;

import hotciv.framework.*;

public class GammaUnitAction implements UnitActionStrat {
    @Override
    public void performUnitActionAt(Position p) {

        //Set settler
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
        Unit unitB3 = new UnitImpl(GameConstants.SETTLER, Player.BLUE);

            //Check if red settler
            if (p == redSettler) {
                //Remove from world
                redSettler = null;
                //Build city with red as owner
                new CityImpl(Player.RED, p);
            }
            //Check if blue settler
            if (p == blueSettler) {
                //Remove from world
                blueSettler = null;
                //Build city with red as owner
                new CityImpl(Player.BLUE, p);
            }
            //Check if red archer
            if (p == redArcher) {
                //Check if already fortified
                int oldDS = unitR3.getDefensiveStrength()*2;
                if(oldDS == unitR2.getDefensiveStrength()) {
                    //Defensive strength stays same
                    unitR3.getDefensiveStrength();
                }
                else {
                    //Double defensive strength
                    int ds = (unitR2.getDefensiveStrength() * 2);
                    unitR2.setDefenses(ds);
                    //Archer cannot move
                    unitR2.setMoveCount(0);
                }
            }
            //Check if blue archer
            if (p == blueArcher) {
                //Check if already fortified
                int oldDS = unitB3.getDefensiveStrength() * 2;
                if (oldDS == unitB2.getDefensiveStrength()) {
                    //Defensive strength stays same
                    unitB3.getDefensiveStrength();
                }
                //Double defensive strength
                int ds = (unitB2.getDefensiveStrength()) * 2;
                unitB2.setDefenses(ds);
                //Archer cannot move
                unitB2.setMoveCount(0);
            }
        }
    }