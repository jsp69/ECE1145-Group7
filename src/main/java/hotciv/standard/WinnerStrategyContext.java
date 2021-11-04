package hotciv.standard;

import hotciv.framework.*;

import java.util.ArrayList;
import java.util.Collection;

public class WinnerStrategyContext implements Game {

    @Override
    public Tile getTileAt(Position p) {
        return null;
    }

    @Override
    public Unit getUnitAt(Position p) {
        return null;
    }

    @Override
    public City getCityAt(Position p) {
        return null;
    }

    @Override
    public Player getPlayerInTurn() {
        return null;
    }

    @Override
    public Player getWinner() {
        return null;
    }

    public int getAge() {
        return this.getAge();
    }

    @Override
    public boolean moveUnit(Position from, Position to) {
        return false;
    }

    @Override
    public void endOfTurn() {

    }

    @Override
    public void changeWorkForceFocusInCityAt(Position p, String balance) {

    }

    @Override
    public void changeProductionInCityAt(Position p, String unitType) {

    }

    @Override
    public void performUnitActionAt(Position p) {}

    public Collection<Player> getOwners() {
        City[][] cityLoc = new CityImpl[16][16];
        cityLoc[1][1] = new CityImpl(Player.RED);
        cityLoc[4][1] = new CityImpl(Player.BLUE);
        cityLoc[3][2] = new CityImpl(Player.BLUE);

        ArrayList<Player> result = new ArrayList<Player>();
        result.add(cityLoc[1][1].getOwner());
        result.add(cityLoc[4][1].getOwner());
        result.add(cityLoc[3][2].getOwner());
        return result;
    }


}
