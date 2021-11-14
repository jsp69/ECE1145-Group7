package hotciv.framework;

import hotciv.standard.CityImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;

public interface WorldStrat {
    Unit[][] u = new UnitImpl[16][16];
    Tile [][] t = new TileImpl[16][16];
    City [][] c = new CityImpl[16][16];

    void gameBoard();

    City[][] getCitiesArray();
    Tile[][] getTilesArray();
    Unit[][] getUnitsArray();
}
