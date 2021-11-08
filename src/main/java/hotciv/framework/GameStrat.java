package hotciv.framework;

import hotciv.standard.CityImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;

public interface GameStrat {
    // Array index corresponds to unit position unitLoc[Position row][Position Column]
    Unit[][] u = new UnitImpl[16][16];

    // Array index corresponds to tile position tileLoc[Position row][Position Column]
    Tile [][] t = new TileImpl[16][16];

    // Array index corresponds to city position cityLoc[Position row][Position Column]
    City [][] c = new CityImpl[16][16];
    void gameBoard();
    Tile getTileAt(Position P);
    Unit getUnitAt(Position p);
    City getCityAt(Position p);
    City[][] getCitiesArray();
    Tile[][] getTilesArray();
    Unit[][] getUnitsArray();
}
