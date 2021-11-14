package hotciv.framework;

import hotciv.standard.CityImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;

public interface MoveAttackStrat {
    Unit[][] u = new UnitImpl[16][16];
    Tile [][] t = new TileImpl[16][16];
    City [][] c = new CityImpl[16][16];

    boolean moveUnit( Position from, Position to, Game game, City[][] cityLoc, Unit[][] unitLoc );
    City[][] getCitiesArray();
    Tile[][] getTilesArray();
    Unit[][] getUnitsArray();
}
