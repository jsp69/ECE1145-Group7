package hotciv.standard;

import hotciv.framework.*;

public class GammaCivImpl implements GammaCiv {

    //Preliminary set-up for GammaCiv
    private GammaCiv gammaCiv;

    //Set settler
    Position redSettler = new Position(4, 3);
    Position blueSettler = new Position(3,4);

    //Set archer
    Position redArcher = new Position(2, 0);
    Position blueArcher = new Position(0,2);

    // Set units
    Unit unitR1 = new UnitImpl(GameConstants.ARCHER, Player.RED, redArcher);
    Unit unitR2 = new UnitImpl(GameConstants.SETTLER, Player.RED, redSettler);
    Unit unitB1 = new UnitImpl(GameConstants.ARCHER, Player.BLUE, blueArcher);
    Unit unitB2 = new UnitImpl(GameConstants.SETTLER, Player.BLUE, blueSettler);

    public GammaCivImpl(SettlerBuildStrategy settlerBuildStrategy) {
    }

    public City settlerRedAction(Position p) {
        //Check if red settler
        if(p == redSettler) {
            //Remove from world
            redSettler = null;
            //Return new city with red as owner
            return new CityImpl(Player.RED, p);
        }
        else {
            return null;
        }
    }

    public City settlerBlueAction(Position p) {
        //Check if blue settler
        if(p == blueSettler) {
            //Remove from world
            blueSettler = null;
            //Return new city with blue as owner
            return new CityImpl(Player.BLUE, p);
        }
        else {
            return null;
        }
    }

    public UnitImpl redArcherFortify(Position p) {
        //Check if red archer
        if(p == redArcher) {
            //Double defensive strength
            int ds = (unitR1.getDefensiveStrength()) * 2;
            unitR1.setDefenses(ds);
            //Settler cannot move
            unitR1.setMoveCount(0);
            //Return new unit with red as owner
            return new UnitImpl("archer", Player.RED, p);
        }
        else {
            return null;
        }
    }

    public UnitImpl blueArcherFortify(Position p) {
        //Check if blue archer
        if(p == redArcher) {
            //Double defensive strength
            int ds = (unitB1.getDefensiveStrength()) * 2;
            unitB1.setDefenses(ds);
            //Settler cannot move
            unitB1.setMoveCount(0);
            //Return new unit with red as owner
            return new UnitImpl("archer", Player.BLUE, p);
        }
        else {
            return null;
        }
    }
}
