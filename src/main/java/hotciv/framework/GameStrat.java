package hotciv.framework;

public interface GameStrat {
    void gameBoard();
    Tile getTileAt(Position P);
    Unit getUnitAt(Position p);
    City getCityAt(Position p);
    City[][] getCitiesArray();
    Tile[][] getTilesArray();
    Unit[][] getUnitsArray();
}
